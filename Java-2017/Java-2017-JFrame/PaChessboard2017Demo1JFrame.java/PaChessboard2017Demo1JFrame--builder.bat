@echo off

set strProjName=PaChessboard2017Demo1JFrame
echo Begin ...
echo --------------------
md classes
::md classes\res
javac -d classes %strProjName%.java
echo --------------------
echo Done.

if exist %strProjName%.manifest.txt del /f %strProjName%.manifest.txt

echo Manifest-Version: 1.0> %strProjName%.manifest.txt
echo Created-By: 1.4.2_12 (Sun Microsystems Inc.)>> %strProjName%.manifest.txt
echo Main-Class: %strProjName%>> %strProjName%.manifest.txt

echo Release ...
echo --------------------
md Release
cd classes
jar cvfm ..\Release\%strProjName%.jar ..\%strProjName%.manifest.txt %strProjName%.class
cd ..
del %strProjName%.manifest.txt
echo --------------------
echo Done.

pause
