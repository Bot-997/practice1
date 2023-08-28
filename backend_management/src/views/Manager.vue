<template>
  <div style="height: 100%">
    <el-container style="height: 100%; ">
      <!-- 侧边栏 -->
      <left-menu :isCollapse="isCollapse"></left-menu>

      <el-container>
        <!-- 顶部 -->
        <el-header style="border-bottom: 1px solid #ccc;">
          <Header :is-collapse="isCollapse" @collapse="collapse"></Header>
        </el-header>

        <el-main>
          <router-view />
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
  import LeftMenu from "../components/LeftMenu";
  import Header from "../components/Header";

  export default {
    data() {
      return {
        isCollapse: false,
        // desc:true
      }
    },
    components:{
      LeftMenu,
      Header,
    },
    created() {
      // 请求分页数据
      // this.load()
    },
    methods: {
      collapse(isCollapse) {
        this.isCollapse = isCollapse;
      },

    }
  }
</script>
