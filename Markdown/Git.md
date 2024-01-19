# Git

### 工作流程

##### 1. clone克隆

从远程仓库中克隆代码到本地仓库

##### 2. checkout检出

从本地仓库中检出一个仓库分支然后进行修订

##### 3.add添加

在提交前先将代码提交到暂存区

##### 4.commit提交

提交到本地仓库，本地仓库中保存修改的各个历史版本

##### 5.fetch抓取

从远程库抓取到本地库，不进行任何的合并动作，一般操作较少

##### 6.pull拉取

从远程库拉到本地库，自动进行合并（merge），然后放到工作区

相当于fetch+merge

##### 7.push推送

修改完成后，需要和团队成员共享代码时，将代码推送到远程仓库



### 基本配置

##### 1.设置用户信息

git config --global user.name "XXX"

git config --global user.email "XXX@XXX.com"

##### 2.查看配置信息

git config--global user.name

git config --global user.email

##### 3.为常用指令配置别名

1. 创建 .bashsrc文件
2. alias {别名}='{原命令}‘
3. 命令行source .bashrc



### 基本使用

##### git init

在文件夹打开Git Bash ,将文件夹初始化为一个本地仓库，成功则在文件夹内有.git文件夹

##### 版本指令

![image-20240117152236798](C:\Users\86159\AppData\Roaming\Typora\typora-user-images\image-20240117152236798.png)

##### 分支指令![image-20240117152402785](C:\Users\86159\AppData\Roaming\Typora\typora-user-images\image-20240117152402785.png)

##### 有关分支

- 可以使用另一切换指令git switch

  创建并切换到新的`dev`分支：

  ```
  $ git switch -c dev
  ```

  直接切换到已有的`master`分支：

  ```
  $ git switch master
  ```

- 通常，合并分支时，如果可能，Git会用`Fast forward`模式，但这种模式下，删除分支后，会丢掉分支信息。

  如果要强制禁用`Fast forward`模式，Git就会在merge时生成一个新的commit，这样，从分支历史上就可以看出分支信息。

  禁用方式之一：添加参数`--no-ff`

  ```
  $ git merge --no-ff -m "merge with no-ff" dev
  Merge made by the 'recursive' strategy.
   readme.txt | 1 +
   1 file changed, 1 insertion(+)
  ```

- 多分支的协作

  ![image-20240117155809287](C:\Users\86159\AppData\Roaming\Typora\typora-user-images\image-20240117155809287.png)

##### SSH key

本地Git仓库和GitHub仓库之间通过SSH加密，SSH设置如下：

- 创建SSH key。检查用户主目录下是否有.ssh目录，以及其中是否有id_rsa和id_rsa.pub这两个文件。如果没有需要创建SSH key：

```
$ ssh-keygen -t rsa -C "youremail@example.com"
```

​	`id_rsa`和`id_rsa.pub`两个文件，这两个就是SSH Key的秘钥对，`id_rsa`是私钥，不能泄露出去，`id_rsa.pub`是公钥，可以放心地告诉任何人。

​	检查config文件内容

```
Host github.com
User git
Hostname ssh.github.com
PreferredAuthentications publickey
IdentityFile ~/.ssh/id_rsa
Port 443

Host gitlab.com
Hostname altssh.gitlab.com
User git
Port 443
PreferredAuthentications publickey
IdentityFile ~/.ssh/id_rsa
```

- 检查是否成功

  ```
  ssh -T git@github.com
  ```

- 在Github账户设置里Add SSH key，把公钥粘贴进去



### 添加远程库

1. 在Github创建新仓库

2. 在本地仓库运行命令

   ```
   $ git remote add origin {仓库地址}
   ```

​	3.此时，远程仓库名字默认就是origin

​	4.然后用git push命令把本地库的内容push到远程库，实际上 是把当前分支master推送到远程。

由于远程库是空的，我们第一次推送`master`分支时，加上了`-u`参数，Git不但会把本地的`master`分支内容推送的远程新的`master`分支，还会把本地的`master`分支和远程的`master`分支关联起来，在以后的推送或者拉取时就可以简化命令。

```
$ git push -u origin master
```

​	5.可能会遇到的SSH警告

```
The authenticity of host 'github.com (xx.xx.xx.xx)' can't be established.
RSA key fingerprint is xx.xx.xx.xx.xx.
Are you sure you want to continue connecting (yes/no)?
```

​	输入yes确认GitHub的Key的指纹信息确实来自GitHub服务器

6.从现在起，只要本地作了提交，就可以通过命令：

```
$ git push origin master
```

把本地`master`分支的最新修改推送至GitHub



### 删除远程库

先用`git remote -v`查看远程库信息：

```
$ git remote -v
origin  git@github.com:michaelliao/learn-git.git (fetch)
origin  git@github.com:michaelliao/learn-git.git (push)
```

然后，根据名字删除，比如删除`origin`：

```
$ git remote rm origin
```

此处的“删除”其实是解除了本地和远程的绑定关系，并不是物理上删除了远程库。远程库本身并没有任何改动。要真正删除远程库，需要登录到GitHub，在后台页面找到删除按钮再删除。



### 从远程库克隆

假设我们从零开发，那么最好的方式是先创建远程库，然后，从远程库克隆

1.在GitHub创建新仓库，勾选`Initialize this repository with a README`，这样GitHub会自动为我们创建一个`README.md`文件

2.用命令`git clone`克隆一个本地库：

```
$ git clone {仓库地址}
Cloning into 'gitskills'...
remote: Counting objects: 3, done.
remote: Total 3 (delta 0), reused 0 (delta 0), pack-reused 3
Receiving objects: 100% (3/3), done.
```



### 分支管理

##### Stash

- 把当前工作现场“储藏”起来，等以后恢复现场后继续工作：

  ```
  $ git stash
  Saved working directory and index state WIP on dev: f52c633 add merge
  ```

- 查看

  ```
  $ git stash list
  stash@{0}: WIP on dev: f52c633 add merge
  ```

- 恢复

  用`git stash pop`，恢复的同时把stash内容也删了：

  ```
  $ git stash pop
  On branch dev
  Changes to be committed:
    (use "git reset HEAD <file>..." to unstage)
  
  	new file:   hello.py
  
  Changes not staged for commit:
    (use "git add <file>..." to update what will be committed)
    (use "git checkout -- <file>..." to discard changes in working directory)
  
  	modified:   readme.txt
  
  Dropped refs/stash@{0} (5d677e2ee266f39ea296182fb2354265b91b3b2a)
  ```

- 在master分支上修复bug后，dev分支仍然存在bug，可以直接将修复了bug的这个提交复制到dev上，不用把整个master分支merge过来

  为了方便操作，Git专门提供了一个`cherry-pick`命令，让我们能复制一个特定的提交到当前分支：

  ```
  $ git branch
  * dev
    master
  $ git cherry-pick {提交编号}
  [master 1d4b803] fix bug 101
   1 file changed, 1 insertion(+), 1 deletion(-)
  ```



##### Feature

开发一个新feature，最好新建一个分支；

如果要丢弃一个没有被合并过的分支，可以通过`git branch -D <name>`强行删除。



##### 多人协作

###### 推送分支

```
$ git push origin dev
```

- `master`分支是主分支，因此要时刻与远程同步；
- `dev`分支是开发分支，团队所有成员都需要在上面工作，所以也需要与远程同步；
- bug分支只用于在本地修复bug，就没必要推到远程了，除非老板要看看你每周到底修复了几个bug；
- feature分支是否推到远程，取决于你是否和你的小伙伴合作在上面开发。



###### 抓取分支

最新提交和你试图推送的提交有冲突，因为远程分支比你的本地更新，需要先用`git pull`试图合并

```
$ git pull
```



###### 通常模式

1. 首先，可以试图用`git push origin <branch-name>`推送自己的修改；
2. 如果推送失败，则因为远程分支比你的本地更新，需要先用`git pull`试图合并；
3. 如果合并有冲突，则解决冲突，并在本地提交；
4. 没有冲突或者解决掉冲突后，再用`git push origin <branch-name>`推送就能成功！

- https://www.liaoxuefeng.com/wiki/896043488029600/900375748016320)



##### Rebase整理提交记录

- 变基
- 把本地未push的分叉提交历史整理成直线
- 使得我们在查看历史提交的变化时更容易