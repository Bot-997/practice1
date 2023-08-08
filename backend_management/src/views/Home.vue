<template>
  <div style="height: 100%">
    <el-container style="height: 100%; ">
      <!-- 侧边栏 -->
      <el-aside :width="isCollapse? '64px' : '200px'" style="background-color: dimgray; overflow: hidden;">
        <div style="height: 60px; line-height: 60px; text-align: center">
          <img src="../assets/logo.png" style="width: 20px; vertical-align: middle">
          <b v-show="!isCollapse" style="color: white">后台管理系统</b>
        </div>
        <!-- 侧边栏目录 -->
        <el-menu :default-openeds="['1', '3']"
                 style="height: 100%; min-height: 100vh; overflow-x: hidden; border: none"
                 active-text-color="yellow"
                 background-color="dimgray"
                 text-color ="#fff"
                 :collapse = "isCollapse"
                 :collapse-transition = "false"
        >
          <el-submenu index="1">
            <template slot="title">
              <i class="el-icon-message"></i>
              <span>导航一</span>
            </template>
            <el-menu-item-group>
              <template slot="title">分组一</template>
              <el-menu-item index="1-1">选项1</el-menu-item>
              <el-menu-item index="1-2">选项2</el-menu-item>
            </el-menu-item-group>
            <el-menu-item-group title="分组2">
              <el-menu-item index="1-3">选项3</el-menu-item>
            </el-menu-item-group>
            <el-submenu index="1-4">
              <template slot="title">选项4</template>
              <el-menu-item index="1-4-1">选项4-1</el-menu-item>
            </el-submenu>
          </el-submenu>
          <el-submenu index="2">
            <template slot="title">
              <i class="el-icon-message"></i>
              <span>导航二</span>
            </template>
            <el-menu-item-group>
              <template slot="title">分组一</template>
              <el-menu-item index="2-1">选项1</el-menu-item>
              <el-menu-item index="2-2">选项2</el-menu-item>
            </el-menu-item-group>
            <el-menu-item-group title="分组2">
              <el-menu-item index="2-3">选项3</el-menu-item>
            </el-menu-item-group>
            <el-submenu index="2-4">
              <template slot="title">选项4</template>
              <el-menu-item index="2-4-1">选项4-1</el-menu-item>
            </el-submenu>
          </el-submenu>
          <el-submenu index="3">
            <template slot="title">
              <i class="el-icon-message"></i>
              <span>导航三</span>
            </template>
            <el-menu-item-group>
              <template slot="title">分组一</template>
              <el-menu-item index="3-1">选项1</el-menu-item>
              <el-menu-item index="3-2">选项2</el-menu-item>
            </el-menu-item-group>
            <el-menu-item-group title="分组2">
              <el-menu-item index="3-3">选项3</el-menu-item>
            </el-menu-item-group>
            <el-submenu index="3-4">
              <template slot="title">选项4</template>
              <el-menu-item index="3-4-1">选项4-1</el-menu-item>
            </el-submenu>
          </el-submenu>
        </el-menu>
      </el-aside>

      <el-container>
        <el-header style="font-size: 12px; border-bottom: 1px solid #ccc; display: flex">
          <div style="flex: 1; font-size: 18px">
            <span :class= "isCollapse ? 'el-icon-s-unfold' : 'el-icon-s-fold'" style="cursor: pointer" @click="collapse"></span>
          </div>
          <el-dropdown style="width: 70px; cursor: pointer">
            <span>王小虎</span><i class="el-icon-arrow-down" style="margin-left: 5px"></i>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item>profile</el-dropdown-item>
              <el-dropdown-item>退出</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </el-header>

        <el-main>
          <!-- 搜索框 -->
          <div style="padding: 10px 0">
            <el-input style="width: 300px" placeholder="请输入名称" suffix-icon="el-icon-search"></el-input>
            <el-input class="ml-5" style="width: 300px" placeholder="请输入邮箱" suffix-icon="el-icon-message"></el-input>
            <el-input class="ml-5" style="width: 300px" placeholder="请输入地址" suffix-icon="el-icon-position"></el-input>
            <el-button class="ml-5" type="primary">搜索</el-button>
          </div>


          <!-- 数据主体 -->
          <el-table :data="tableData">
            <el-table-column prop="date" label="日期" width="140">
            </el-table-column>
            <el-table-column prop="name" label="姓名" width="120">
            </el-table-column>
            <el-table-column prop="address" label="地址">
            </el-table-column>
          </el-table>

          <!-- 分页 -->
          <div class="block" style="padding: 10px 0">
            <el-pagination
                    :page-sizes="[5, 10, 15, 20]"
                    :page-size="10"
                    layout="total, sizes, prev, pager, next, jumper"
                    :total="400">
            </el-pagination>
          </div>
        </el-main>

      </el-container>
    </el-container>
  </div>
</template>

<style>
  .el-header {
    color: #333;
    line-height: 60px;
  }

</style>


<script>
  export default {
    data() {
      const item = {
        date: '2016-05-02',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1518 弄'
      };
      return {
        tableData: Array(5).fill(item),
        isCollapse: false,
        collapseBtnClass: 'el-icon-s-fold',
        sideWidth: 200
      }
    },
    methods: {
      collapse() {
        this.isCollapse = !this.isCollapse;
        this.collapseBtnClass = this.isCollapse ? 'el-icon-s-unfold' : 'el-icon-s-fold'
        // this.collapseBtnClass = 'el-icon-s-unfold'
      }
    }
  }
</script>
