<template>
  <el-menu :default-openeds="opens" style="min-height: 100%; overflow-x: hidden"
           background-color="rgb(0, 0, 0)"
           text-color="#fff"
           active-text-color="#ffd04b"
           :collapse-transition="false"
           :collapse="isCollapse"
           router
  >
    <div style="height: 60px; line-height: 60px; text-align: center">
      <img src="../assets/logo1.jpeg" alt="" style="width: 20px; position: relative; top: 5px; right: 5px">
      <b style="color: white" v-show="logoTextShow">CAMPUS CONNECT</b>
    </div>
<!--    <div v-for="item in menus" :key="item.id">-->
<!--      <div v-if="item.path">-->
<!--        <el-menu-item :index="item.path">-->
<!--          <i class="el-icon-house"></i>-->
<!--          <span slot="title">{{ item.name }}</span>-->
<!--        </el-menu-item>-->
<!--      </div>-->
<!--      <div v-else>-->
<!--        <el-submenu :index="item.id + ''">-->
<!--          <template slot="title">-->
<!--            <i class="el-icon-menu"></i>-->
<!--            <span slot="title">{{ item.name }}</span>-->
<!--          </template>-->
<!--          <div  v-for="subItem in item.children" :key="subItem.id">-->
<!--            <el-menu-item :index="subItem.path">-->
<!--              <i class="el-icon-s-custom"></i>-->
<!--              <span slot="title">{{ subItem.name }}</span>-->
<!--            </el-menu-item>-->
<!--          </div>-->
<!--        </el-submenu>-->
<!--      </div>-->
<!--    </div>-->

    <el-menu-item index="/home">
      <template slot="title">
        <i class="el-icon-house"></i>
        <span slot="title">HOME</span>
      </template>
    </el-menu-item>
    <el-submenu index="2">
      <template slot="title">
        <i class="el-icon-menu"></i>
        <span slot="title">SYSTEM</span>
      </template>
      <el-menu-item index="/user">
        <i class="el-icon-s-custom"></i>
        <span slot="title">USER</span>
      </el-menu-item>
      <el-menu-item index="/role">
        <i class="el-icon-s-custom"></i>
        <span slot="title">Role</span>
      </el-menu-item>
      <el-menu-item index="/menu">
        <i class="el-icon-s-custom"></i>
        <span slot="title">Menu</span>
      </el-menu-item>
      <el-menu-item index="/course">
        <i class="el-icon-reading"></i>
        <span slot="title">Course</span>
      </el-menu-item>
      <el-menu-item index="/mycourse">
        <i class="el-icon-reading"></i>
        <span slot="title">MyCourse</span>
      </el-menu-item>
    </el-submenu>
  </el-menu>
</template>

<script>
export default {
  name: "Aside",
  props: {
    isCollapse: Boolean,
    logoTextShow: Boolean
  },
  data() {
    return {
      menus: localStorage.getItem("menus") ? JSON.parse(localStorage.getItem("menus")) : [],
      opens: localStorage.getItem("menus") ? JSON.parse(localStorage.getItem("menus")).map(v => v.id + '') : []
    }
  },
}
</script>

<style scoped>

</style>
