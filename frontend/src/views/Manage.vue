<template>
  <el-container style="min-height: 100vh">

    <el-aside :width="sideWidth + 'px'" style="box-shadow: 2px 0 6px rgb(0 21 41 / 35%);">
      <Aside :isCollapse="isCollapse" :logoTextShow="logoTextShow" />
    </el-aside>
    <el-container>
      <el-header style="border-bottom: 1px solid #ccc;">
        <Header :collapseBtnClass="collapseBtnClass" :collapse="collapse" />
      </el-header>
      <el-main>
        <router-view />
      </el-main>
    </el-container>

  </el-container>
</template>

<script>

import Aside from "@/components/Aside";
import Header from "@/components/Header";

export default {
  name: 'Home',
  data() {
    return {
      collapseBtnClass: 'el-icon-s-fold',
      isCollapse: false,
      sideWidth: 200,
      logoTextShow: true,
    }
  },
  components: {
    Aside,
    Header
  },
  methods: {
    collapse() {
      this.isCollapse = !this.isCollapse
      if (this.isCollapse) {
        this.sideWidth = 64
        this.collapseBtnClass = 'el-icon-s-unfold'
        this.logoTextShow = false
      } else {
        this.sideWidth = 200
        this.collapseBtnClass = 'el-icon-s-fold'
        this.logoTextShow = true
      }
    }
  }
}
</script>

<!--<template>-->
<!--  <el-container style="min-height: 100vh">-->

<!--    <el-aside :width="sideWidth + 'px'" style="box-shadow: 2px 0 6px rgb(0 21 41 / 35%);">-->
<!--      <el-menu :default-openeds="['1', '3']" style="min-height: 100%; overflow-x: hidden"-->
<!--               background-color="rgb(48, 65, 86)"-->
<!--               text-color="#fff"-->
<!--               active-text-color="#ffd04b"-->
<!--               :collapse-transition="false"-->
<!--               :collapse="isCollapse"-->
<!--      >-->
<!--        <div style="height: 60px; line-height: 60px; text-align: center">-->
<!--          <img src="../assets/logo.png" alt="" style="width: 20px; position: relative; top: 5px; right: 5px">-->
<!--          <b style="color: white" v-show="logoTextShow">New Campus Connect</b>-->
<!--        </div>-->
<!--        <el-submenu index="1">-->
<!--          <template slot="title">-->
<!--            <i class="el-icon-message"></i>-->
<!--            <span slot="title">NAVIGATION ONE</span>-->
<!--          </template>-->
<!--          <el-menu-item-group title="Group2">-->
<!--            <el-menu-item index="1-3">Option 3</el-menu-item>-->
<!--          </el-menu-item-group>-->
<!--          <el-submenu index="1-4">-->
<!--            <template slot="title">Option 4</template>-->
<!--            <el-menu-item index="1-4-1">Option4-1</el-menu-item>-->
<!--          </el-submenu>-->
<!--        </el-submenu>-->
<!--        <el-submenu index="2">-->
<!--          <template slot="title">-->
<!--            <i class="el-icon-menu"></i>-->
<!--            <span slot="title">Navigation Two</span>-->
<!--          </template>-->
<!--          <el-submenu index="2-4">-->
<!--            <template slot="title">Option4</template>-->
<!--            <el-menu-item index="2-4-1">Option4-1</el-menu-item>-->
<!--          </el-submenu>-->
<!--        </el-submenu>-->
<!--        <el-submenu index="3">-->
<!--          <template slot="title">-->
<!--            <i class="el-icon-setting"></i>-->
<!--            <span slot="title">Navigation Three</span>-->
<!--          </template>-->
<!--          <el-submenu index="3-4">-->
<!--            <template slot="title">Option4</template>-->
<!--            <el-menu-item index="3-4-1">Option4-1</el-menu-item>-->
<!--          </el-submenu>-->
<!--        </el-submenu>-->
<!--      </el-menu>-->
<!--    </el-aside>-->

<!--    <el-container>-->
<!--      <el-header style="font-size: 12px; border-bottom: 1px solid #ccc; line-height: 60px; display: flex">-->
<!--        <div style="flex: 1; font-size: 20px">-->
<!--          <span :class="collapseBtnClass" style="cursor: pointer" @click="collapse"></span>-->
<!--        </div>-->
<!--        <el-dropdown style="width: 70px; cursor: pointer">-->
<!--          <span>James</span><i class="el-icon-arrow-down" style="margin-left: 5px"></i>-->
<!--          <el-dropdown-menu slot="dropdown" >-->
<!--            <el-dropdown-item style="font-size: 14px; padding: 5px 0">Personal Information</el-dropdown-item>-->
<!--            <el-dropdown-item style="font-size: 14px; padding: 5px 0">Sign Out</el-dropdown-item>-->
<!--          </el-dropdown-menu>-->
<!--        </el-dropdown>-->

<!--      </el-header>-->

<!--      <el-main>-->
<!--        <div style="margin-bottom: 30px">-->
<!--          <el-breadcrumb separator="/">-->
<!--            <el-breadcrumb-item :to="{ path: '/' }">HomePage</el-breadcrumb-item>-->
<!--            <el-breadcrumb-item>User Management</el-breadcrumb-item>-->
<!--          </el-breadcrumb>-->
<!--        </div>-->

<!--        <div style="margin: 10px 0">-->
<!--          <el-input style="width: 200px" placeholder="Please input name" suffix-icon="el-icon-search" v-model="username"></el-input>-->
<!--&lt;!&ndash;          <el-input style="width: 200px" placeholder="Please input email" suffix-icon="el-icon-message" class="ml-5" v-model="email"></el-input>&ndash;&gt;-->
<!--&lt;!&ndash;          <el-input style="width: 200px" placeholder="Please input address" suffix-icon="el-icon-position" class="ml-5" v-model="address"></el-input>&ndash;&gt;-->
<!--          <el-button class="ml-5" type="primary" @click="load">Search</el-button>-->
<!--          <el-button type="warning" @click="reset">Reset</el-button>-->
<!--        </div>-->

<!--        <div style="margin: 10px 0">-->
<!--          <el-button type="primary" @click="handleAdd">ADD <i class="el-icon-circle-plus-outline"></i></el-button>-->
<!--          <el-popconfirm-->
<!--              class="ml-5"-->
<!--              confirm-button-text='YES'-->
<!--              cancel-button-text='NO'-->
<!--              icon="el-icon-info"-->
<!--              icon-color="red"-->
<!--              title="Are you sure delete all？"-->
<!--              @confirm="delBatch"-->
<!--          >-->
<!--          <el-button type="danger" slot="reference">DELETE ALL <i class="el-icon-remove-outline"></i></el-button>-->
<!--          </el-popconfirm>-->
<!--&lt;!&ndash;          <el-button type="primary">IMPORT <i class="el-icon-bottom"></i></el-button>&ndash;&gt;-->
<!--&lt;!&ndash;          <el-button type="primary">EXPORT <i class="el-icon-top"></i></el-button>&ndash;&gt;-->
<!--        </div>-->

<!--        <el-table :data="tableData" border stripe :header-cell-class-name="headerBg" @selection-change="handleSelectionChange">-->
<!--          <el-table-column type="selection" width="55"></el-table-column>-->
<!--          <el-table-column prop="id" label="ID" width="80"></el-table-column>-->
<!--          <el-table-column prop="username" label="USER" width="140"></el-table-column>-->
<!--          <el-table-column prop="lastname" label="LASTNAME" width="120"></el-table-column>-->
<!--          <el-table-column prop="email" label="EMAIL"></el-table-column>-->
<!--          <el-table-column prop="phone" label="PHONE"></el-table-column>-->
<!--          <el-table-column prop="address" label="ADDRESS"></el-table-column>-->
<!--          <el-table-column label="OPERATION"  width="200" align="center">-->
<!--            <template slot-scope="scope">-->
<!--              <el-button type="primary" @click="handleEdit(scope.row)">EDIT <i class="el-icon-edit"></i></el-button>-->
<!--              <el-popconfirm-->
<!--                  class="ml-5"-->
<!--                  confirm-button-text='YES'-->
<!--                  cancel-button-text='NO'-->
<!--                  icon="el-icon-info"-->
<!--                  icon-color="red"-->
<!--                  title="Are you sure delete？"-->
<!--                  @confirm="del(scope.row.id)"-->
<!--              >-->
<!--              <el-button type="danger" slot="reference">DELETE <i class="el-icon-remove-outline"></i></el-button>-->
<!--              </el-popconfirm>-->
<!--            </template>-->
<!--          </el-table-column>-->
<!--        </el-table>-->
<!--        <div style="padding: 10px 0">-->
<!--          <el-pagination-->
<!--              @size-change="handleSizeChange"-->
<!--              @current-change="handleCurrentChange"-->
<!--              :current-page="pageNum"-->
<!--              :page-sizes="[2, 5, 10, 20]"-->
<!--              :page-size="pageSize"-->
<!--              layout="total, sizes, prev, pager, next, jumper"-->
<!--              :total="total">-->
<!--          </el-pagination>-->
<!--        </div>-->

<!--        <el-dialog title="User Information" :visible.sync="dialogFormVisible" width="30%" >-->
<!--          <el-form label-width="80px" size="small">-->
<!--            <el-form-item label="username">-->
<!--              <el-input v-model="form.username" autocomplete="off"></el-input>-->
<!--            </el-form-item>-->
<!--            <el-form-item label="lastname">-->
<!--              <el-input v-model="form.lastname" autocomplete="off"></el-input>-->
<!--            </el-form-item>-->
<!--            <el-form-item label="email">-->
<!--              <el-input v-model="form.email" autocomplete="off"></el-input>-->
<!--            </el-form-item>-->
<!--            <el-form-item label="phone">-->
<!--              <el-input v-model="form.phone" autocomplete="off"></el-input>-->
<!--            </el-form-item>-->
<!--            <el-form-item label="address">-->
<!--              <el-input v-model="form.address" autocomplete="off"></el-input>-->
<!--            </el-form-item>-->
<!--          </el-form>-->
<!--          <div slot="footer" class="dialog-footer">-->
<!--            <el-button @click="dialogFormVisible = false">CANCEL</el-button>-->
<!--            <el-button type="primary" @click="save">CONFIRM</el-button>-->
<!--          </div>-->
<!--        </el-dialog>-->

<!--      </el-main>-->

<!--    </el-container>-->
<!--  </el-container>-->
<!--</template>-->

<!--<script>-->
<!--export default {-->
<!--  name: 'Home',-->
<!--  data() {-->
<!--    return {-->
<!--      tableData: [],-->
<!--      total: 0,-->
<!--      pageNum: 1,-->
<!--      pageSize: 2,-->
<!--      username: "",-->
<!--      // email: "",-->
<!--      // address: "",-->
<!--      form: {},-->
<!--      dialogFormVisible: false,-->
<!--      multipleSelection: [],-->
<!--      msg: "hello 452",-->
<!--      collapseBtnClass: 'el-icon-s-fold',-->
<!--      isCollapse: false,-->
<!--      sideWidth: 200,-->
<!--      logoTextShow: true,-->
<!--      headerBg: 'headerBg'-->
<!--    }-->
<!--  },-->
<!--  created() {-->
<!--    this.load()-->
<!--  },-->
<!--  methods: {-->
<!--    collapse() {-->
<!--      this.isCollapse = !this.isCollapse-->
<!--      if (this.isCollapse) {-->
<!--        this.sideWidth = 64-->
<!--        this.collapseBtnClass = 'el-icon-s-unfold'-->
<!--        this.logoTextShow = false-->
<!--      } else {-->
<!--        this.sideWidth = 200-->
<!--        this.collapseBtnClass = 'el-icon-s-fold'-->
<!--        this.logoTextShow = true-->
<!--      }-->
<!--    },-->
<!--    load() {-->
<!--      this.request.get("/user/page", {-->
<!--        params: {-->
<!--          pageNum: this.pageNum,-->
<!--          pageSize: this.pageSize,-->
<!--          username: this.username,-->
<!--          // email: this.email,-->
<!--          // address: this.address,-->
<!--        }-->
<!--      }).then(res => {-->
<!--        console.log(res)-->

<!--        this.tableData = res.records-->
<!--        this.total = res.total-->

<!--      })-->
<!--    },-->
<!--    save() {-->
<!--      this.request.post("/user", this.form).then(res => {-->
<!--        if (res) {-->
<!--          this.$message.success("Save Success")-->
<!--          this.dialogFormVisible = false-->
<!--          this.load()-->
<!--        } else {-->
<!--          this.$message.error("Save Failure")-->
<!--        }-->
<!--      })-->
<!--    },-->
<!--    handleAdd() {-->
<!--      this.dialogFormVisible = true-->
<!--      this.form = {}-->
<!--    },-->
<!--    handleEdit(row) {-->
<!--      this.form = row-->
<!--      this.dialogFormVisible = true-->
<!--    },-->
<!--    del(id) {-->
<!--      this.request.delete("/user/" + id).then(res => {-->
<!--        if (res) {-->
<!--          this.$message.success("Delete Success")-->
<!--          this.load()-->
<!--        } else {-->
<!--          this.$message.error("Delete Failure")-->
<!--        }-->
<!--      })-->
<!--    },-->
<!--    handleSelectionChange(val) {-->
<!--      console.log(val)-->
<!--      this.multipleSelection = val-->
<!--    },-->
<!--    delBatch() {-->
<!--      let ids = this.multipleSelection.map(v => v.id)  // [{}, {}, {}] => [1,2,3]-->
<!--      this.request.post("/user/del/batch", ids).then(res => {-->
<!--        if (res) {-->
<!--          this.$message.success("Delete Batch Success")-->
<!--          this.load()-->
<!--        } else {-->
<!--          this.$message.error("Delete Batch Failure")-->
<!--        }-->
<!--      })-->
<!--    },-->
<!--    reset() {-->
<!--      this.username = ""-->
<!--      // this.email = ""-->
<!--      // this.address = ""-->
<!--      this.load()-->
<!--    },-->
<!--    handleSizeChange(pageSize) {-->
<!--      console.log(pageSize)-->
<!--      this.pageSize = pageSize-->
<!--      this.load()-->
<!--    },-->
<!--    handleCurrentChange(pageNum) {-->
<!--      console.log(pageNum)-->
<!--      this.pageNum = pageNum-->
<!--      this.load()-->
<!--    }-->
<!--  }-->
<!--}-->
<!--</script>-->

<!--<style>-->
<!--.headerBg {-->
<!--  background: #eee!important;-->
<!--}-->
<!--</style>-->
