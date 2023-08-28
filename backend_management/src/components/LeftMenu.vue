<template>
    <el-aside :width="isCollapse? '64px' : '200px'" style="background-color: dimgray; overflow: hidden;">
        <!-- 侧边栏目录 -->
        <el-menu router :default-openeds="['1', '3']"
                 style="height: 100%; min-height: 100vh; overflow-x: hidden; border: none"
                 active-text-color="yellow"
                 background-color="dimgray"
                 text-color ="#fff"
                 :collapse = "isCollapse"
                 :collapse-transition = "false"
        >
            <div style="height: 60px; line-height: 60px; text-align: center">
                <img src="../assets/logo.png" style="width: 20px; vertical-align: middle" alt="">
                <b v-show="!isCollapse" style="color: white">后台管理系统</b>
            </div>
            <el-menu-item index="/">
                <template slot="title">
                    <i class="el-icon-house"></i>
                    <span>主页</span>
                </template>
            </el-menu-item>
            <el-submenu index="/">
                <template slot="title">
                    <i class="el-icon-menu"></i>
                    <span>系统管理</span>
                </template>

                <el-menu-item index="/userData">
                    <template slot="title">
                        <i class="el-icon-s-custom"></i>
                        <span>用户管理</span>
                    </template>
                </el-menu-item>
            </el-submenu>
        </el-menu>
    </el-aside>
</template>

<script>
    export default {
        name: "LeftMenu",
        props:{
            isCollapse: Boolean,
        }
    }
</script>

<style scoped>

</style>
