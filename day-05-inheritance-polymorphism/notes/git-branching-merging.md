# Git Branching and Merging

## 1. Why Branch?
Branching allows you to diverge from the main line of development (`main` or `master`) and continue to do work (like adding a new feature or fixing a bug) without messing up the stable code.

## 2. Branching Commands
*   `git branch`: List all local branches.
*   `git branch [name]`: Create a new branch.
*   `git switch [name]` or `git checkout [name]`: Switch to the specified branch.
*   `git switch -c [name]`: Create a new branch AND switch to it immediately.

## 3. Merging
Once your work on a feature branch is complete and tested, you merge it back into the main branch.
1.  Switch to the receiving branch: `git switch main`
2.  Merge the feature branch into it: `git merge feature-branch-name`

## 4. Merge Conflicts
A conflict happens when two branches have changed the same part of the same file, and Git cannot figure out which version to keep.

**How to resolve a conflict:**
1. Git pauses the merge and modifies the affected files to show the conflict using markers.
2. Open the file in your editor. You will see something like this:
    ```text
    <<<<<<< HEAD
    System.out.println("Code from main branch");
    =======
    System.out.println("Code from feature branch");
    >>>>>>> feature-branch-name
    ```
3. Decide which code to keep (or combine them). Delete the Git markers (`<<<<<<<`, `=======`, `>>>>>>>`).
4. Save the file.
5. Stage the resolved file: `git add <file>`
6. Complete the merge by committing: `git commit -m "Resolved merge conflict"`
