#!/bin/sh

# controlla che il nome del file da scaricare non e nel file dei downloads

IS_ALR_DOWN="false"
LOCAL_PATH=~/Music/
EXT_FILE=mp3
DOWN_FILE_NAME=downloadsTest.log
DOWN_FILES=$LOCAL_PATH$DOWN_FILE_NAME

function addToCsv(){
    echo "  addToCsv $1"
    ##ls -rt $LOCAL_PATH*$1*$EXT_FILE >> $LOG_DOWN_FILES
    for file in $LOCAL_PATH*
    do
        if [[ -f $file ]]; then
          echo "  $file" >> $DOWN_FILES
        fi
    done
}


NAME_N_1="20180905_cr31.mp3"

if grep -q -e $NAME_N_1 $DOWN_FILES; then
    IS_ALR_DOWN="true"
    echo "scaricato" $IS_ALR_DOWN
else
    IS_ALR_DOWN="false"
    echo $NAME_N_1 >> $DOWN_FILES
    echo "da scaricare" $IS_ALR_DOWN
fi


if [[ "$IS_ALR_DOWN" = "true" ]] ; then
    echo "e' true."
else
    echo "e' false."
fi

addToCsv 20180804


echo "----------------------------------------------------------------------"
echo ""
exit;