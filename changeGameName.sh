#!/bin/bash
if [ -z "$1" ]
  then
    echo "Supply a new GameName to use as argument"
    exit 1
fi
# look out below
cd ..
if [[ ! -d GameName ]];then
    echo "Top directory, 'GameName', does not exist, abandon ship!"
    exit 1
fi
echo "GameName exists, making changes"
echo -n "using ${1} for GameName "
low=`echo $1 | awk '{print tolower($1)}'`
echo "and com.$low for package"
echo "removing all git info, you'll need to re-initialize"
rm -rf GameName/.git 
rm -f GameName/README.md 
UpList=`grep -lr GameName GameName | grep -v changeGameName.sh | tr '\n' ' '`
sed -i s/GameName/"$1"/g $UpList
LowList=`grep -lr gamename GameName | grep -v changeGameName.sh | tr '\n' ' '`
sed -i s/gamename/"$low"/g $LowList
mv GameName/app/src/main/java/com/gamename GameName/app/src/main/java/com/${low}
mv GameName/app/src/main/java/com/${low}/GameNameThread.java GameName/app/src/main/java/com/${low}/${1}Thread.java
mv GameName/app/src/main/java/com/${low}/GameNameView.java GameName/app/src/main/java/com/${low}/${1}View.java
mv GameName/app/src/test/java/com/gamename GameName/app/src/test/java/com/${low}
mv GameName/app/src/androidTest/java/com/gamename GameName/app/src/androidTest/java/com/${low}
mv GameName ${1}
echo "GameName is now ${1}"
