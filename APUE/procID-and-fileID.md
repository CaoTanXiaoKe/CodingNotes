## 用户ID与文件ID

#### 与进程相关的ID

与一个进程相关联的ID有 6 个或更多。

- 实际用户ID和实际组ID:	标识我们实际是谁，这两个字段在登录时取自口令文件中的登录项。 通常在一个登录会话期间这些值并不改变，但是超级用户进程有方法改变它们。 

- 有效用户ID，有效组ID和附属组ID: 用于文件访问权限检查，决定了我们的文件访问权限。 

- 保存的**设置用户ID**和**设置组ID**: 在执行一个程序时包含了有效用户ID和有效组ID的副本，这两项由exec函数保存。


通常情况下，有效用户ID等于实际用户ID, 有效组ID等于实际组ID. 

每个文件都有一个所有者和组所有者，所有者由stat结构中的st_uid指定，组所有者则由st_gid指定。 

当执行一个程序文件时，进程的有效用户ID就是实际用户ID, 有效组ID就是实际组ID. 但是可以在文件模式字（st_mode）中设置一个特殊标志，其含义是“当执行此文件时，将进程的有效用户ID设置为文件所有者的用户ID”。 与此相类似，在文件模式字里面可以设置另一位，它将执行此文件的进程的有效组ID设置为文件的组所有者ID, 在文件模式字中，这两位被称为**设置用户ID(set-user-ID)** 和 **设置组ID(set-group-ID)**。 

例如，如果文件所有者是超级用户， 而且设置了该用户的设置用户ID位，那么当该程序文件由一个进程执行时，该进程具有超级用户权限。 不管执行此文件的进程的实际用户ID是什么，都会是这样。 例如 系统函数 passwd(1)就是这样的一个函数，它是设置用户ID的， 可以使得用户进程把新口令写入口令文件中（一般是/etc/passwd 或 /etc/shadow), 而只有超级用户才具有该文件的写权限。因此运行设置用户ID程序的进程通常会得到额外的权限，所以编写这类程序的时候应该更加谨慎。 

再回到 stat 函数， 设置用户 ID 位及设置组 ID 位都包含在文件 st_mode 值中， 这两位可分别用常量 S_ISUID 和 S_ISGID 测试。 
 

#### 文件访问权限

st_mode值也包含了对文件的访问权限位。每个文件都有 9 个访问权限位，可以用`ls -l filename`进行查看。 
- S_IRUSR, S_IWUSR, S_IXUSR：分别对应 用户读，用户写，用户执行。 
- S_IRGRP, S_IWGRP, S_IXRGP: 分别对应 组读，组写，组执行。
- S_IROTH, S_IWOTH, S_IXOTH: 分别对应 其他读，其他写，其他执行。

---

进程每次打开，创建和删除一个文件时，内核就进行文件访问权限测试，而这种测试可能涉及文件的所有者（st_uid 和 st_gid）, 进程的有效ID(有效用户ID和有效组ID)以及进程的附属组ID(如果支持的话)。两个所有者ID是文件的性质，而两个有效ID和附属组ID则是进程的性质。内核进行的测试具体如下：

1. If the effective user ID of the process is 0 (the superuser), access is allowed. This
gives the superuser free rein throughout the entire file system.

2. If the effective user ID of the process equals the owner ID of the file (i.e., the 
process owns the file), access is allowed if the appropriate user access
permission bit is set. Otherwise, permission is denied. By appropriate access
permission bit, we mean that if the process is opening the file for reading, the
user-read bit must be on. If the process is opening the file for writing, the
user-write bit must be on. If the process is executing the file, the user-execute bit
must be on.

3. If the effective group ID of the process or one of the supplementary group IDs of
the process equals the group ID of the file, access is allowed if the appropriate
group access permission bit is set. Otherwise, permission is denied.

4. If the appropriate other access permission bit is set, access is allowed.
Otherwise, permission is denied.

These four steps are tried in sequence. Note that if the process owns the file
(step 2), access is granted or denied based only on the user access permissions; the
group permissions are never looked at. Similarly, if the process does not own the file
but belongs to an appropriate group, access is granted or denied based only on the
group access permissions; the other permissions are not looked at.

#### 更改用户ID和更改组ID

可以使用`setuid`函数设置实际用户ID和有效用户ID. 与此类似，可以使用`setgid`函数设置实际组ID和有效组ID. 

```c
#include <unistd.h>
int setuid(uid_t uid);
int setgid(gid_t gid);
									Both return: 0 if OK, −1 on error
```

关于谁能更改ID有若干规则。 现在先考虑更改用户 ID 的规则（关于用户ID我们所说明的一切都适用于组ID）. 

1. 若进程具有超级用户特权，则 setuid函数将实际用户ID, 有效用户ID以及保存的设置用户ID(saved set-user-ID) 设置为 uid. 
2. 若进程没有超级用户特权，但是 uid 等于实际用户ID或保存的设置用户ID, 则setuid只将有效用户ID设置为 uid. 不更改实际用户ID和保存的设置用户ID. 
3. 如果上面两个条件都不满足，则 errno 设置为 EPERM, 并返回 -1. 

关于内核所维护的 3 个用户ID, 还需要注意一下几点。
1. Only a superuser process can change the real user ID. Normally, the real user
ID is set by the login(1) program when we log in and never changes. Because
login is a superuser process, it sets all three user IDs when it calls setuid.

2. The effective user ID is set by the exec functions only if the set-user-ID bit is set
for the program file. If the set-user-ID bit is not set, the exec functions leave the
effective user ID as its current value. We can call setuid at any time to set the
effective user ID to either the real user ID or the saved set-user-ID. Naturally,
we can’t set the effective user ID to any random value.

3. The saved set-user-ID is copied from the effective user ID by exec. If the file’s
set-user-ID bit is set, this copy is saved after exec stores the effective user ID
from the file’s user ID. 

