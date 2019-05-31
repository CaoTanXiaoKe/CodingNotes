## VSCode C++ 配置


### 快捷键
- `Command + 鼠标左键`，是文件、函数等跳转。
- `Ctrl + -` ，是跳转后返回原处。
- `Command + Shift + O`，列出函数名
- `Command + P`，列出近期打开的文件名
- `Ctrl +Tab`, 可以列出最最最近打开的文件，在开发时，两个文件间切换时效率很高。
- 上述是最常用的功能。其他功能请参考[VSCode官网快捷键](https://code.visualstudio.com/shortcuts/keyboard-shortcuts-macos.pdf)

### 配置代理
- 用户首选项
```
    "http.proxy": "http://dev-proxy.oa.com:8080/",
    "http.proxyStrictSSL": false,
```

- 或安装全局代理软件: proxifier

### 安装插件
- C/C++
- [vscode_cpptools](https://github.com/Microsoft/vscode-cpptools/releases) (可能需要手动安装)
    - 按住`Ctrl+Shfit+P`，在弹窗的输入框中输入`Install from VSIX` 





### 参考资料
- [VScode for C++ 开发利器](http://km.oa.com/group/626/articles/show/359056?kmref=search&from_page=1&no=1)

