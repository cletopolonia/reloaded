#!/bin/sh

## variables
REMOT_PATH_OLD=http://cdn.flv.kataweb.it/deejay/audio/
REMOT_PATH=https://media.deejay.it/legacy/audio/
REMOT_SEP=/

LOCAL_PATH=~/Music/
LOCAL_SEP=_

PT_NAME=""
TRUE="true"
FALSE="false"
IS_ALR_DOWN=$FALSE

DOWN_FILE_NAME=downloads.log
LOG_DOWN_FILES=$LOCAL_PATH$DOWN_FILE_NAME
LOG_WGET_FILE=~/Documents/logs/local/wget.log
LOG_TAG_FILE=~/Documents/logs/local/tagclear.log
EXT_FILE=.mp3

PARAM_ELAB_E=-e
PARAM_ELAB_S=-s
PARAM_ELAB_N=-n
PARAM_ELAB_U=-u
PARAM_ELAB_P=-p
PARAM_ELAB_T=-t
PARAM_ELAB_D=* # parametro di default

CR31_LOCAL=cr31
CR31_REMOT=chiamate_roma_triuno_triuno
DJCI_LOCAL=djci
DJCI_REMOT=deejay_chiama_italia
TROP_LOCAL=niki
TROP_REMOT=tropical_pizza
DJFC_LOCAL=djfc
DJFC_REMOT=deejay_football_club
CORD_LOCAL=cord
CORD_REMOT=cordialmente

# rimuovere e sostituire per ottenere pinocchio
#PINO_LOCAL=pino
#PINO_REMOT=pinocchio
#declare -a PROG_NAMES_REMOT=($CR31_REMOT $DJCI_REMOT $TROP_REMOT $PINO_REMOT $DJFC_REMOT $CORD_REMOT);
#declare -a PROG_NAMES_LOCAL=($CR31_LOCAL $DJCI_LOCAL $TROP_LOCAL $PINO_LOCAL $DJFC_LOCAL $CORD_LOCAL);

# array dei programmi da scaricare
declare -a PROG_NAMES_REMOT=($CR31_REMOT $DJCI_REMOT $TROP_REMOT $DJFC_REMOT $CORD_REMOT);
declare -a PROG_NAMES_LOCAL=($CR31_LOCAL $DJCI_LOCAL $TROP_LOCAL $DJFC_LOCAL $CORD_LOCAL);

# today
TODAY=$(date +%Y%m%d)
# yesterday
# YESTERDAY=$(date +%Y%m%d -d "-1 days")
# tomorrow
# TOMORROW=$(date +%Y%m%d -d "+1 days")

# functions
function setStartDate() {
    START_DATE=$1
}

function setEndDate() {
    END_DATE=$1
}

function setNDayBefore() {
    N_DAY_BEFORE=$(date +%Y%m%d -d "-$1 days")
}

function increaseDate(){
    TMP_DATE=`date '+%C%y%m%d' -d "$1+1 days"`
}

function downloadFromUrl() {
    echo "  downloadFromUrl from $2"
    echo "  downloadFromUrl to   $1"
    wget -a $LOG_WGET_FILE -O $1 $2
    echo "  file saved      to   $1"
}

function setNameProgLocal() {
    TMP_NAME_LOCAL=${PROG_NAMES_LOCAL[$1]}
}

function setNameProgRemot() {
    TMP_NAME_REMOT=${PROG_NAMES_REMOT[$1]}
}

function setFileRemot() {
    REMOT_FILE=$REMOT_PATH$1$REMOT_SEP$2$EXT_FILE
}

function setFileLocal() {
    PT_NAME=$2$LOCAL_SEP$1$EXT_FILE
    LOCAL_FILE=$LOCAL_PATH$PT_NAME
}

function removeEmptyFiles() {
    echo "  removeEmptyFiles $LOCAL_PATH*$1*$EXT_FILE"
    find $LOCAL_PATH*$1*$EXT_FILE -size -10k -type f -exec rm -f '{}' \;
}

function resetTags() {
    echo "  resetTags $LOCAL_PATH*$1*$EXT_FILE"
    find $LOCAL_PATH*$1*$EXT_FILE -exec nohup mid3v2 -D $LOCAL_PATH$1*$EXT_FILE > $LOG_TAG_FILE '{}' \;
}

function setIsDownloaded(){
    if grep -q -e $1 $LOG_DOWN_FILES; then
        IS_ALR_DOWN=$TRUE
    else
        IS_ALR_DOWN=$FALSE
    fi
}

function addToCsvOld(){
   echo "  addToCsv $1"
   ls -rt $LOCAL_PATH*$1*$EXT_FILE >> $LOG_DOWN_FILES
}

function validateParams(){
    if [[ "$#" -ne 4 ]] ; then
        echo "Illegal number of parameters"
        echo " - required 4 vs $#" 
        exit 1
    fi
}

function addToCsv(){
   echo "  addToCsv $1"
    for file in $( ls -rt $LOCAL_PATH*$1*$EXT_FILE ); do
        #echo "File: $file"
        if grep -q -e $file $LOG_DOWN_FILES; then
            echo "  $file gia scaricato" 
        else
            #echo "ADD del file: $file"
            echo $file >> $LOG_DOWN_FILES
        fi
    done

}

function generateFileRemot() {
    NAME_TO_FIND=$1
    echo "  generateFileRemot find $NAME_TO_FIND"
    for INDEX_TMP in "${!PROG_NAMES_LOCAL[@]}"; do
       if [[ "${PROG_NAMES_LOCAL[$INDEX_TMP]}" = "${NAME_TO_FIND}" ]]; then
           echo "  $NAME_TO_FIND found." 
           setNameProgRemot ${INDEX_TMP};
       fi
    done
    if [[ ! $TMP_NAME_REMOT ]] ; then
        echo "  program $NAME_TO_FIND NOT found."
        return
    fi
    setFileRemot $TMP_NAME_REMOT $2
}

## set dei parametri:
function setParams(){
    if [[ ! $1 ]] ; then
          echo "scarico tutte le puntate di oggi" 
          setParams $PARAM_ELAB_S $TODAY
          return
    fi
    case $1 in
        $PARAM_ELAB_E)
            echo "-e 2 date from/to = -e yyyyMMgg yyyyMMgg"
            setStartDate $2
            if [[ ! $3 ]] ; then
                setEndDate $TODAY
            else    
                setEndDate $3
            fi
            ;;
         $PARAM_ELAB_S)
            echo "-s single day = -s yyyyMMgg"
            setStartDate $2
            setEndDate $2
            ;;
         $PARAM_ELAB_N)
            echo "-n day in past = -n #NUM_INT"
            setNDayBefore $2
            setStartDate $N_DAY_BEFORE
            setEndDate $N_DAY_BEFORE
            ;; 
         $PARAM_ELAB_U)
            echo "-u url = -u http... yyyyMMgg PROG_NAME"
            validateParams $1 $2 $3 $4
            setFileLocal $4 $3
            downloadFromUrl $LOCAL_FILE $2
            removeEmptyFiles $3
            resetTags $3
            addToCsv $3
            return
            ;;      
        $PARAM_ELAB_P)
            echo "-p single program = -p yyyyMMgg PROG_NAME"
            generateFileRemot $3 $2
            setFileLocal $3 $2
            downloadFromUrl $LOCAL_FILE $REMOT_FILE
            removeEmptyFiles $2
            resetTags $2
            addToCsv $2
            return
            ;;      
        $PARAM_ELAB_T)
            echo "TEST----->"
            validateParams $1 $2 $3 $4
            return
            ;;  
        $PARAM_ELAB_D)
            echo "parametro: [$1] non valido."
            echo "usage"
            echo "-e from              = -e 20190706                               --> scarica tutte le puntate dal 6 luglio fino ad oggi"
            echo "-e from/to           = -e 20190706 20190709                      --> scarica tutte le puntate dal 6 al 9 luglio"
            echo "-s single day        = -s 20190706                               --> scarica tutte le puntate del 6 luglio"
            echo "-n day in past       = -n 5                                      --> scarica tutte le puntate di 5 gg fa"
            echo "-u url               = -u http://www./././djci.mp3 20190706 djci --> scarica il file djci.mp3 dall'url gli da il nome 20190706_djci.mp3"
            echo "-p single program    = -p 20190706 djci                          --> scarica la puntata di djci del 20190706"
            echo "-t test single func  = -t some input                             --> test"
            return
            ;;
    esac
}

## flow
echo "start"
echo ""
echo "----------------------------------------------------------------------"

setParams $@

echo 
echo "from: $START_DATE"  
echo "to  : $END_DATE"
echo 

#tmp_date
TMP_DATE=$START_DATE

#ciclo le date
while (($TMP_DATE<=$END_DATE )) ; do
    echo "date: $TMP_DATE"
    #ciclo i programmi
    for (( INDEX=0; INDEX<${#PROG_NAMES_REMOT[@]}; INDEX+=1 )) ; do
        setNameProgLocal $INDEX
        setNameProgRemot $INDEX
        setFileRemot $TMP_NAME_REMOT $TMP_DATE
        setFileLocal $TMP_NAME_LOCAL $TMP_DATE
        setIsDownloaded $PT_NAME
        if [[ "$IS_ALR_DOWN" = "$TRUE" ]] ; then
            echo "  it's here: $LOCAL_FILE"
        else
            downloadFromUrl $LOCAL_FILE $REMOT_FILE
        fi
        IS_ALR_DOWN=$FALSE
    done

    removeEmptyFiles $TMP_DATE
    #fare ll tmp_date, così scopro quali ho scaricato, ed aggiungerli nel files.
    addToCsv $TMP_DATE
    resetTags $TMP_DATE
    increaseDate $TMP_DATE
    echo ""
done

echo ""
echo "----------------------------------------------------------------------"
echo ""
#echo "move pinocchio"
#echo "mv $LOCAL_PATH*_pino.mp3 /home/biadmin/Documents/altro/pinocchio/"
#cd $LOCAL_PATH && mv *_pino.mp3 /home/biadmin/Documents/altro/pinocchio/

echo "end"
clear
exit 0