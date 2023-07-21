
# FileDeleter

The FileDeleter Tool is an application designed to delete files from multiple folders simultaneously. It is particularly useful for clearing application-generated files and cache folders from domains on application servers like GlassFish.

## Description

Use this tool at your own risk. Always ensure you have backups of important files before performing cleanup operations.

## Features

The FileDeleter Tool GUI allows the user to select directories that need to be cleaned up and delete files from those directories. The GUI provides the following functionalities:

- Displaying the selected directories in a tree view window.
- Enabling or disabling the cleanup of directories by checking or unchecking the corresponding checkboxes.
- Adding new directories to be cleaned up.
- Removing directories from the list of directories to be cleaned up.
- Deleting files from the selected directories.


## Usage
1. Start the FileDeleter.

2. The GUI will launch, displaying the tree view window where the selected directories will be shown.

3. To add new directories, click on the "add directory" button. It will open a file browser window where you can select the desired directory.

4. The selected directories will be added to the tree view window and will be enabled (the checkboxes will be checked).

5. To remove directories from the list, select the respective directory in the tree view window and click on the "remove directory" button. The directory will be removed from the list.

6. To delete files from the selected directories, click on the "delete files" button. A confirmation dialog will appear to ensure that you want to delete all files in the marked directories. If you confirm, the files will be permanently deleted.

7. If desired, you can manually edit the configuration file "config.txt". This file contains the list of selected directories and their status (enabled/disabled). You can add or remove directories or change their status by opening the file with a text editor and making the necessary changes.

## Note
- Make sure to back up important files before deleting files, as the deleted files cannot be recovered.

- The GUI uses the de.deloma.tools.filedeleter.Controler class as the controller to manage user interactions. Data access operations are performed by the de.deloma.tools.filedeleter.OrdnerDaoImpl class, which uses the "config.txt" file to store the list of selected directories and their status (enabled/disabled).

## Disclaimer
Use this tool at your own risk. Always ensure you have backups of important files before performing cleanup operations.

### Contribution
Contributions are welcome! If you find issues or have suggestions for improvement, please create a pull request or open an issue.
