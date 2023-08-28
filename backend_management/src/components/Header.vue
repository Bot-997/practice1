<template>
    <div style="line-height: 60px; display: flex">
        <div style="flex: 1; font-size: 18px">
            <span :class= "isCollapse ? 'el-icon-s-unfold' : 'el-icon-s-fold'" style="cursor: pointer" @click="collapse"></span>

            <el-breadcrumb separator-class="el-icon-arrow-right" style="display: inline-block; margin-left: 20px">
                <el-breadcrumb-item :to="item.path" v-for="item in paths">item.name</el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <el-dropdown style="width: 70px; cursor: pointer">
            <span>王小虎</span><i class="el-icon-arrow-down" style="margin-left: 5px"></i>
            <el-dropdown-menu slot="dropdown">
                <el-dropdown-item>profile</el-dropdown-item>
                <el-dropdown-item>退出</el-dropdown-item>
            </el-dropdown-menu>
        </el-dropdown>
    </div>
</template>

<script>
    export default {
        name: "Header",
        props: {
            isCollapse: Boolean
        },
        data() {
            return {
                paths: [],
            }
        },
        methods:{
            collapse() {
                this.isCollapse = !this.isCollapse;
                this.$emit("collapse", this.isCollapse)
            },
        }
    }
</script>

<style scoped>

</style>
