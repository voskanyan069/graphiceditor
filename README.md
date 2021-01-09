# Graphic Editor
## Java Installation
### Ubuntu 16.04/18.04
```shell
sudo add-apt-repository ppa:linuxuprising/java
sudo apt-get update
sudo apt-get install oracle-java11-set-default
```
### Ubuntu 20.04
```shell
sudo apt install default-jre
```
## App Installation
### Linux
```shell
sudo apt-get update
sudo apt-get upgrade
sudo apt-get install git
```
#### Method 1
1) Clone project to your pc
2) Change the current directory to project directory
3) Give execute permissions to run.sh and install.sh files
4) Install javafx libraries
5) Run a editor
```shell
git clone https://github.com/voskanyan069/graphiceditor/
cd graphiceditor
chmod +x run.sh install.sh
./install.sh
./run.sh
```
If you want you can move the GraphicEditor.jar run.sh lib/ to another directory
1) Move GraphicEditor.jar run.sh lib/ files and directory
2) Go to moved directory
3) Run a editor
```shell
mv GraphicEditor.jar run.sh lib/ /path/to/move
cd /path/to/moved_files
./run.sh
```
#### Method 2
1) Clone project to your pc
2) Change the current directory to project directory
3) Move GraphicEditor.jar run.sh installer.sh files to another directory
4) Change the current directory to moved files directory
5) Give execute permissions to run.sh and install.sh files
6) Install javafx libraries
7) Run a editor
```shell
git clone https://github.com/voskanyan069/graphiceditor/
cd graphiceditor
mv GraphicEditor.jar run.sh installer.sh /path/to/move
cd /path/to/moved_files
chmod +x run.sh install.sh
./install.sh
./run.sh
```
## TODO
- ~~Add text~~
- Crop image
- Screen color picker tool
- Gradient fill of pictures
- Move drawn pictures in canvas
