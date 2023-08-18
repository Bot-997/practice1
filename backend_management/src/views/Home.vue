<template>
  <div style="height: 100%">
    <el-container style="height: 100%; ">
      <!-- 侧边栏 -->
      <el-aside :width="isCollapse? '64px' : '200px'" style="background-color: dimgray; overflow: hidden;">
        <div style="height: 60px; line-height: 60px; text-align: center">
          <img src="../assets/logo.png" style="width: 20px; vertical-align: middle" alt="">
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
          </el-submenu>
        </el-menu>
      </el-aside>

      <el-container>
        <!-- 顶部 -->
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

          <el-breadcrumb separator-class="el-icon-arrow-right" style="padding: 10px 0">
            <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
            <el-breadcrumb-item>User1</el-breadcrumb-item>
          </el-breadcrumb>

          <!-- 搜索框 -->
          <div style="padding: 10px 0">
            <el-input style="width: 300px" placeholder="请输入名称" suffix-icon="el-icon-search" v-model="username"></el-input>
            <el-input class="ml-5" style="width: 300px" placeholder="请输入邮箱" suffix-icon="el-icon-message" v-model="email"></el-input>
            <el-input class="ml-5" style="width: 300px" placeholder="请输入地址" suffix-icon="el-icon-position" v-model="address"></el-input>
            <el-button class="ml-5" type="primary" @click="load">搜索</el-button>
            <el-button class="ml-5" type="warning" @click="reset">清空</el-button>
          </div>

          <div style="margin: 10px 0">
            <el-button type="primary" @click="handleAdd">新增 <i class="el-icon-circle-plus-outline"></i></el-button>
            <el-popconfirm title="确定批量删除吗？"
                           confirm-button-text='确定'
                           cancel-button-text='不用了'
                           icon="el-icon-info"
                           icon-color="red"
                           @confirm="handleDelBatch"
                           class="ml-5">
              <el-button type="danger" slot="reference">批量删除 <i class="el-icon-remove-outline"></i></el-button>
            </el-popconfirm>
            <el-button type="primary" class="ml-5" @click="">导入 <i class="el-icon-bottom"></i></el-button>
            <el-button type="primary" @click="">导出 <i class="el-icon-top"></i></el-button>

            <el-switch style="float: right; margin-right: 50px"
                       v-model="desc"
                       @change="load"
                       inactive-color="#13ce66"
                       active-text="倒序"
                       inactive-text="正序">
            </el-switch>
          </div>

          <!-- 数据主体 -->
          <el-table :data="tableData" stripe @selection-change="handleSelectionChange">
            <el-table-column type="selection" width="50" ></el-table-column>
            <el-table-column prop="id" label="ID" width="80" />
            <el-table-column prop="username" label="用户名" width="140" />
            <el-table-column prop="nickname" label="昵称" width="120" />
            <el-table-column prop="email" label="邮箱" width="120" />
            <el-table-column prop="phone" label="电话" width="120" />
            <el-table-column prop="address" label="地址" />
            <el-table-column label="操作">
              <template slot-scope="scope">
                <el-button type="success" @click="handleEdit(scope.row)">编辑 <i class="el-icon-edit-outline"></i></el-button>
                <el-popconfirm title="确定删除吗？"
                               confirm-button-text='确定'
                               cancel-button-text='不用了'
                               icon="el-icon-info"
                               icon-color="red"
                               @confirm="handleDel(scope.row.id)"
                               class="ml-5">
                  <el-button type="danger" slot="reference">删除 <i class="el-icon-remove-outline"></i></el-button>
                </el-popconfirm>
              </template>
            </el-table-column>
          </el-table>

          <!-- 分页 -->
          <div class="block" style="padding: 10px 0">
            <el-pagination
                    @size-change="handleSizeChange"
                    @current-change="handleCurrentChange"
                    :current-page="pageNum"
                    :page-sizes="[5, 10, 15, 20]"
                    :page-size="pageSize"
                    layout="total, sizes, prev, pager, next, jumper"
                    :total="total">
            </el-pagination>
          </div>

          <!-- 新增 -->
          <el-dialog title="新增用户" :visible.sync="dialogFormVisible" width="30%">
            <el-form label-width="100px">
              <el-form-item label="用户名">
                <el-input v-model="form.username" autocomplete="off"></el-input>
              </el-form-item>
              <el-form-item label="昵称">
                <el-input v-model="form.nickname" autocomplete="off"></el-input>
              </el-form-item>
              <el-form-item label="邮箱">
                <el-input v-model="form.email" autocomplete="off"></el-input>
              </el-form-item>
              <el-form-item label="电话">
                <el-input v-model="form.phone" autocomplete="off"></el-input>
              </el-form-item>
              <el-form-item label="地址">
                <el-input v-model="form.address" autocomplete="off"></el-input>
              </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
              <el-button @click="dialogFormVisible = false">取 消</el-button>
              <el-button type="primary" @click="save">确 定</el-button>
            </div>
          </el-dialog>

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
  import request from "../utils/request";

  export default {
    data() {
      return {
        tableData: [],
        total: 0,
        pageNum: 1,
        pageSize: 5,
        username: '',
        email: '',
        address: '',
        form: {},
        isCollapse: false,
        collapseBtnClass: 'el-icon-s-fold',
        sideWidth: 200,
        multipleSelection: [],
        dialogFormVisible: false,
        desc:true
      }
    },
    created() {
      // 请求分页数据
      this.load()
    },
    methods: {
      collapse() {
        this.isCollapse = !this.isCollapse;
        this.collapseBtnClass = this.isCollapse ? 'el-icon-s-unfold' : 'el-icon-s-fold'
        // this.collapseBtnClass = 'el-icon-s-unfold'
      },
      load(){
        if (this.desc) {
          request.get('/user/page/desc', {
            params: {
              pageNum: this.pageNum,
              pageSize: this.pageSize,
              username: this.username,
              email: this.email,
              address: this.address
            }
          }).then(res => {
            this.tableData = res.records
            this.total = res.total
          })
        }else {
          request.get('/user/page/asc', {
            params: {
              pageNum: this.pageNum,
              pageSize: this.pageSize,
              username: this.username,
              email: this.email,
              address: this.address
            }
          }).then(res => {
            this.tableData = res.records
            this.total = res.total
          })
        }
      },
      save(){
        request.post('/user',this.form).then(res => {
          if (res) {
            this.$message.success('保存成功')
            this.load()
            this.dialogFormVisible = false
          } else {
            this.$message.error('保存失败')
          }
        })
      },
      handleSizeChange(pageSize){
        this.pageSize = pageSize;
        this.load()
      },
      handleCurrentChange(pageNum) {
        this.pageNum = pageNum
        this.load()
      },
      handleAdd() {
        this.dialogFormVisible = true
        this.form = {}
      },
      handleEdit(row) {
        this.form = row
        this.dialogFormVisible = true
      },
      handleDel(id) {
        request.delete('/user/del/' + id).then(res => {
          if (res) {
            this.$message.success('删除成功')
            this.load()
          } else {
            this.$message.error('删除成功')
          }
        })
      },
      handleDelBatch() {
        let ids = this.multipleSelection.map(v => v.id)
        request.post('/user/del/batch', ids).then(res => {
          if (res) {
            this.$message.success('删除成功')
            this.load()
          } else {
            this.$message.error('删除成功')
          }
        })
      },
      handleSelectionChange (val) {
        this.multipleSelection = val
      },
      reset(){
        this.username = ''
        this.email = ''
        this.address = ''
        this.load()
      }
    }
  }
</script>
