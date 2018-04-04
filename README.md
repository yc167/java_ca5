# java_ca5
Step 1 : install git hub desktop and more specifically, git bash (the terminal)

Step 2 : so u are in the bash terminal, navigate to a directory, 
example : /d/Document/NetBeansProject/  (or u can create a new folder GitHub so u wont confuse)
in this directory, create a folder exp: mkdir java_ca5, and then navigate to the folder  by using cd java_ca5

Step 3 : initiate git repository
type git init

Step 4 : connect your local repo to the remote repo in Github
 git remote add origin https://github.com/yc167/java_ca5.git

Step 5 : pull the codes from github to your local repo
git pull origin master
u will see stuff like counting objs, compressing and unpacking etc
if its done, go check it and verify that its the project we have been working on

Step 6 : commit 
if u make any changes to the codes in netbeans, u have to commit first before pushing to github
u can either : git commit -m "Your message about the commit"
or simply use commit function in netbeans

Step 7 : pushing
either : git push origin master
,or use push function in netbeans

Try them and see if it work for you
