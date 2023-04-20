# deleting the directory
rm -rf target

# creating the new directory (because java -d doesn't create it)
mkdir target

# set the destination directory for class files
javac src/java/edu/school21/printer/*/*.java -d ./target

# specify where to find user class files
java -classpath ./target edu.school21.printer.app.Program . 0 /home/regulusb/java/it.bmp
