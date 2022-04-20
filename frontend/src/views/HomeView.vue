<template>
  <el-container style="min-height: 100vh">

    <el-aside :width="sideWidth + 'px'" style="box-shadow: 2px 0 6px rgb(0 21 41 / 35%);">
      <el-menu :default-openeds="['1', '3']" style="min-height: 100%; overflow-x: hidden"
               background-color="rgb(48, 65, 86)"
               text-color="#fff"
               active-text-color="#ffd04b"
               :collapse-transition="false"
               :collapse="isCollapse"
      >
        <div style="height: 60px; line-height: 60px; text-align: center">
          <img src="../assets/logo.png" alt="" style="width: 20px; position: relative; top: 5px; right: 5px">
          <b style="color: white" v-show="logoTextShow">New Campus Connect</b>
        </div>
        <el-submenu index="1">
          <template slot="title">
            <i class="el-icon-message"></i>
            <span slot="title">NAVIGATION ONE</span>
          </template>
          <el-menu-item-group title="Group2">
            <el-menu-item index="1-3">Option 3</el-menu-item>
          </el-menu-item-group>
          <el-submenu index="1-4">
            <template slot="title">Option 4</template>
            <el-menu-item index="1-4-1">Option4-1</el-menu-item>
          </el-submenu>
        </el-submenu>
        <el-submenu index="2">
          <template slot="title">
            <i class="el-icon-menu"></i>
            <span slot="title">Navigation Two</span>
          </template>
          <el-submenu index="2-4">
            <template slot="title">Option4</template>
            <el-menu-item index="2-4-1">Option4-1</el-menu-item>
          </el-submenu>
        </el-submenu>
        <el-submenu index="3">
          <template slot="title">
            <i class="el-icon-setting"></i>
            <span slot="title">Navigation Three</span>
          </template>
          <el-submenu index="3-4">
            <template slot="title">Option4</template>
            <el-menu-item index="3-4-1">Option4-1</el-menu-item>
          </el-submenu>
        </el-submenu>
      </el-menu>
    </el-aside>

    <el-container>
      <el-header style="font-size: 12px; border-bottom: 1px solid #ccc; line-height: 60px; display: flex">
        <div style="flex: 1; font-size: 20px">
          <span :class="collapseBtnClass" style="cursor: pointer" @click="collapse"></span>
        </div>
        <el-dropdown style="width: 70px; cursor: pointer">
          <span>James</span><i class="el-icon-arrow-down" style="margin-left: 5px"></i>
          <el-dropdown-menu slot="dropdown" >
            <el-dropdown-item style="font-size: 14px; padding: 5px 0">Personal Information</el-dropdown-item>
            <el-dropdown-item style="font-size: 14px; padding: 5px 0">Sign Out</el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>

      </el-header>

      <el-main>
        <div style="margin-bottom: 30px">
          <el-breadcrumb separator="/">
            <el-breadcrumb-item :to="{ path: '/' }">HomePage</el-breadcrumb-item>
            <el-breadcrumb-item>User Management</el-breadcrumb-item>
          </el-breadcrumb>
        </div>

        <div style="margin: 10px 0">
          <el-input style="width: 200px" placeholder="Please input name" suffix-icon="el-icon-search"></el-input>
          <el-input style="width: 200px" placeholder="Please input email" suffix-icon="el-icon-message" class="ml-5"></el-input>
          <el-input style="width: 200px" placeholder="Please input address" suffix-icon="el-icon-position" class="ml-5"></el-input>
          <el-button class="ml-5" type="primary">Search</el-button>
        </div>

        <div style="margin: 10px 0">
          <el-button type="primary">ADD <i class="el-icon-circle-plus-outline"></i></el-button>
          <el-button type="danger">DELETE ALL <i class="el-icon-remove-outline"></i></el-button>
          <el-button type="primary">IMPORT <i class="el-icon-bottom"></i></el-button>
          <el-button type="primary">EXPORT <i class="el-icon-top"></i></el-button>
        </div>

        <el-table :data="tableData" border stripe :header-cell-class-name="headerBg">
          <el-table-column prop="date" label="DATE" width="140">
          </el-table-column>
          <el-table-column prop="name" label="NAME" width="120">
          </el-table-column>
          <el-table-column prop="address" label="ADDRESS">
          </el-table-column>
          <el-table-column label="OPERATION"  width="200" align="center">
            <template slot-scope="scope">
              <el-button type="success">EDIT <i class="el-icon-edit"></i></el-button>
              <el-button type="danger">DELETE <i class="el-icon-remove-outline"></i></el-button>
            </template>
          </el-table-column>
        </el-table>

        <div style="padding: 10px 0">
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
</template>

<script>

export default {
  name: 'Home',
  data() {
    const item = {
      date: '2022-04-19',
      name: 'Jame Bond',
      address: 'South loop, Chicago'
    };
    return {
      tableData: Array(10).fill(item),
      msg: "hello",
      collapseBtnClass: 'el-icon-s-fold',
      isCollapse: false,
      sideWidth: 200,
      logoTextShow: true,
      headerBg: 'headerBg'
    }
  },
  methods: {
    collapse() {  // 点击收缩按钮触发
      this.isCollapse = !this.isCollapse
      if (this.isCollapse) {  // 收缩
        this.sideWidth = 64
        this.collapseBtnClass = 'el-icon-s-unfold'
        this.logoTextShow = false
      } else {   // 展开
        this.sideWidth = 200
        this.collapseBtnClass = 'el-icon-s-fold'
        this.logoTextShow = true
      }
    }
  }
}
</script>

<style>
.headerBg {
  background: #eee!important;
}
</style>