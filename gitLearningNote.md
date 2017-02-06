## Git 学习笔记

学习网址:[廖雪峰Git教程] (http://www.liaoxuefeng.com/wiki/0013739516305929606dd18361248578c67b8067c8c017b000)

#### 1. 安装完成后，需要设置一下
<pre><code>$ git config --global user.name "Your Name"
$ git config --global user.email "email@example.com"
</code></pre>

#### 2. 初始化版本库
<code>$ git init</code>

- 加到暂存区与提交到本地版本库
<pre><code>$ git add <filename>
$ git commit -m "comments" <filename></code></pre>

- 用 <code>git status</code>命令查看工作区状态
- 用 <code>git diff</code>命令查看修改内容

#### 3. 版本回退
- <code> git reset --hard commit_id </code>进行回退
- <code> git log </code> 查看提交历史
- <code> git reflog </code> 查看提交历史，以便确定要回到未来的哪个版本。 
- 删除也是一个修改操作：<code> $ rm <filename> </code> 然后进行 <code>add</code>, <code>commit </code>

#### 4. 连接远程库
- 建立 ssh 连接 <code> $ ssh-keygen -t rsa -C "youremail@example.com"</code>
- 要关联一个远程库，使用命令 <code> git remote add origin git@server-name:path/repo-name.git </code>
- 关联后，使用命令 <code> git push -u origin master </code> 第一次推送master分支的所有内容

- 此后，每次本地提交后。只要有必要，可以使用命令 <code> git push origin master</code> 推送最新提交。 

#### 5. clone 远程仓库
- <code> git clone ... </code>
- Git 支持多种协议， 包括 https, 但是通过 ssh 支持的原生 git 协议速度更快。 

#### 6. 分支管理
- 查看分支： <code> git branch </code>
- 创建分支： <code> git branch <name> </code>
- 切换分支： <code> git checkout <name> </code>
- 创建+切换分支：<code> git checkout -b <name> </code>
- 合并某分支到当前分支： <code> git merge <name> </code>
- 删除某分支： <code> git branch -d <name> </code>
- 当Git无法自动合并分支时，就必须首先解决冲突。 解决好冲突后， 再提交， 合并完成。用 <code>git log --graph </code> 命令何以看到分支合并图。 

- 当修复bug时， 我们通过创建新的bug分支进行修复， 然后合并， 最后删除； 
- 当手头工作没有完成时，先把工作现场 git stash 一下， 然后去修复bug, 修复后， 再<code> git stash pop</code>, 回到工作现场。

- 开发一个新的feature, 最好新建一个分支； 
- 如果要丢弃一个没有被合并过的分支， 可以通过 <code>git branch -D <name> </code> 强行删除。 

- 查看远程库信息： <code> git remote -v </code>; 
- 本地新建的分支如果不推送到远程， 对其他人就是不可见的； 
- 从本地推送分支， 使用<code> git push origin branch-name </code>, 如果失败，先用<code>git pull</code>抓取远程的新提交； 
- 在本地创建和远程分支对应的分支， 使用<code>git checkout -b branch-name origin/branch-name</code>， 本地和远程分支的名称最好一致。 
- 建立本地分支和远程分支的关联， 使用<code> git branch --set-upstream branch-name origin/branch-name</code>; 
- 从远程抓取分支， 使用 <code>git pull</code>， 如果有冲突， 要先处理冲突。 

#### 7. 标签管理
- 命令 <code> git tag <name> </code> 用于新建一个标签， 默认为 <code>HEAD</code>, 也可以指定一个 commit id; 
- <code>git tag -a <tagname> -m "comments" </code>可以指定标签信息； 
- <code>git tag -s <tagname> -m "comments" </code>可以用PGP签名标签； 
- 命令 <code> git tag </code> 可以查看所有标签。 
- <code> git push origin <tagname> </code> 可以推送一个本地标签； 
- <code> git push origin --tags</code> 可以推送全部未推送过的本地标签； 
- <code> git tag -d <tagname> </code> 可以删除一个本地标签； 
- <code> git push origin :refs/tags/<tagname></code> 可以删除一个远程标签。 

#### 8. 忽略特殊文件
[<code>.gitignore</code>文件的常见配置](https://github.com/github/gitignore)

- 忽略文件的原则： 
1. 忽略操作系统自动生成的文件， 比如缩略图等； 
2. 忽略编译生成的中间文件，可执行文件等。 
3. 忽略你自己的带有敏感信息的配置文件，比如存放口令的配置文件。 

[Git Cheat Sheet](https://pan.baidu.com/s/1kU5OCOB#list/path=%2F)

> ### Great work need time, foucs, patience, work hard and put heart into it. 
