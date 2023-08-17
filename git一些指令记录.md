##git和远程仓库
###github上创建了一个仓库，然后和本地连接

git config --global user.name "用户名"

git config --global user.email "邮箱"

添加ssh公钥

ssh-keygen -t rsa -C "邮箱"

其他配置没做 一路回车

cat ~/.ssh/id_rsa.pub 得到key

在github上setting里添加这个key

ssh -T git@github.com验证一下

初始化仓库 git init

git remote add origin 项目的ssh链接

git pull origin main

git add .

git commit -m ""

git push origin main

git push -u origin main