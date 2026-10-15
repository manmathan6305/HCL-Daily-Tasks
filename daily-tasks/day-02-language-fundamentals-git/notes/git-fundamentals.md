# Git Fundamentals

## 1. What is Git?
Git is a distributed version control system. It tracks changes to files over time, allowing you to revert to previous versions and collaborate with others.

## 2. Core Concepts
*   **Working Directory:** The files you currently see and are editing on your computer.
*   **Staging Area (Index):** A holding area where you prepare changes before committing them.
*   **Local Repository (Commit History):** The local `.git` database storing your committed snapshots.
*   **Remote Repository:** A version of your project hosted on the internet (e.g., GitHub, GitLab).

## 3. Basic Git Commands
*   `git init`: Initialize a new local Git repository.
*   `git clone [url]`: Copy a remote repository to your local machine.
*   `git status`: Check the state of your working directory and staging area.
*   `git add [file]`: Move a specific file to the staging area. Use `git add .` to add all modified files.
*   `git commit -m "message"`: Save the staged changes to the local repository with a descriptive message.
*   `git log`: View the commit history.
*   `git push origin main`: Upload your local commits to the remote repository's main branch.
*   `git pull origin main`: Fetch and merge changes from the remote repository to your local machine.

## 4. The .gitignore File
A text file that tells Git which files or folders to ignore completely (e.g., compiled `.class` files, IDE settings, `.log` files).
