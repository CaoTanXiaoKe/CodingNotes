#### git常用命令
- `git fetch`: 相当于是从远程获取最新版本到本地，不会自动merge.
    - `git fetch origin master`
    - `git log -p master ..origin/master`
    - `git merge origin/master`
    - 首先从远程的origin的master主分支下载最新的版本到origin/master分支上
    - 然后比较本地的master分支和origin/master分支的差别
    - 最后进行合并

- `git pull`: 相当于是从远程获取最新版本并merge到本地。
    - `git pull origin master`
    - 相当于 git fetch和git merge。

- 比较： 在实际使用中，git fetch更安全一些。因为在merge前，我们可以查看更新的情况，然后再决定是否合并。



- `git merge`: 
- 

- `git log`
    - `git log pretty=oneline`
    - 简短的显示log信息。 
    - `git log -p -2`
    - `-p`用来显示每次提交的内容差异。 例如可以加上 `-2` 来显示最近两次提交。  

- `git tag`
    - `git tag`
    - 列出已有的标签。
    - `git tag -l 'v1.2*'`
    - 列出以v1.2开头的 tags
    - `git tag -a v1.4 -m "my version 1.4"`
    - `a`的意思是`annotated`。添加带附注的标签。
    - `git show v1.4` 
    - 查看标签信息和对应的提交信息
    - `git tag v1.4-1w`
    - 使用轻量标签。
    - `git log --pretty=oneline`
    - `git tag -a v1.2 <校验和>`
    - 后期打标签：可以先用`log`命令查看提交和校验和，然后打标签。
    - `git push orgin v1.5`
    - 把标签推到远程库。
    - `git push origin --tags`
    - 把本地的标签一次性推送到远程仓库。 
    - `git checkout v2.0.0`
    - 此时会位于一个空分支上，可以通过`cat`命令查看内容。
    - `git checkout -v version2 v2.0.0`
    - 在特定的标签（这里指 v2.0.0）上创建一个新分支。 
    - `git tag -d v2.0.0`
    - 删除标签 v2.0.0

- `git stash`
    - `git stash`
    - `git stash pop`


- `git branch`
    - `git branch dev_local origin/dev`

 

#### 
- 

https://segmentfault.com/a/1190000002918123