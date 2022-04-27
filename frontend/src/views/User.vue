<template>
  <div>
    <div style="margin: 10px 0">
      <el-input style="width: 200px" placeholder="Please Input Username" suffix-icon="el-icon-search" v-model="username"></el-input>
<!--      <el-input style="width: 200px" placeholder="email" suffix-icon="el-icon-message" class="ml-5" v-model="email"></el-input>-->
<!--      <el-input style="width: 200px" placeholder="address" suffix-icon="el-icon-position" class="ml-5" v-model="address"></el-input>-->
      <el-button class="ml-5" type="primary" @click="load">Search</el-button>
      <el-button type="warning" @click="reset">Reset</el-button>
    </div>

    <div style="margin: 10px 0">
      <el-button type="primary" @click="handleAdd">ADD <i class="el-icon-circle-plus-outline"></i></el-button>
      <el-popconfirm
          class="ml-5"
          confirm-button-text='YES'
          cancel-button-text='NO'
          icon="el-icon-info"
          icon-color="red"
          title="Are you sure to delete？"
          @confirm="delBatch"
      >
        <el-button type="danger" slot="reference">DeleteBatch <i class="el-icon-remove-outline"></i></el-button>
      </el-popconfirm>
<!--      <el-button type="primary" class="ml-5">import <i class="el-icon-bottom"></i></el-button>-->
<!--      <el-button type="primary">export <i class="el-icon-top"></i></el-button>-->
    </div>

    <el-table :data="tableData" border stripe :header-cell-class-name="'headerBg'"  @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55"></el-table-column>
      <el-table-column prop="id" label="ID" width="80"></el-table-column>
      <el-table-column prop="username" label="username" width="140"></el-table-column>
      <el-table-column prop="lastname" label="lastname" width="120"></el-table-column>
      <el-table-column prop="email" label="email"></el-table-column>
      <el-table-column prop="phone" label="phone"></el-table-column>
      <el-table-column prop="address" label="address"></el-table-column>
      <el-table-column label="operation"  width="200" align="center">
        <template slot-scope="scope">
          <el-button type="primary" @click="handleEdit(scope.row)">Edit <i class="el-icon-edit"></i></el-button>
          <el-popconfirm
              class="ml-5"
              confirm-button-text='YES'
              cancel-button-text='NO'
              icon="el-icon-info"
              icon-color="red"
              title="Are you sure？"
              @confirm="del(scope.row.id)"
          >
            <el-button type="danger" slot="reference"> Delete<i class="el-icon-remove-outline"></i></el-button>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>

    <div style="padding: 10px 0">
      <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="pageNum"
          :page-sizes="[2, 5, 10, 20]"
          :page-size="pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total">
      </el-pagination>
    </div>

    <el-dialog title="Personal Information" :visible.sync="dialogFormVisible" width="30%" >
      <el-form label-width="80px" size="small">
        <el-form-item label="username">
          <el-input v-model="form.username" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="lastname">
          <el-input v-model="form.lastname" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="email">
          <el-input v-model="form.email" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="phone">
          <el-input v-model="form.phone" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="address">
          <el-input v-model="form.address" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">Cancel</el-button>
        <el-button type="primary" @click="save">Confirm</el-button>
      </div>
    </el-dialog>

  </div>
</template>

<script>
export default {
  name: "User",
  data() {
    return {
      tableData: [],
      total: 0,
      pageNum: 1,
      pageSize: 2,
      username: "",
      email: "",
      address: "",
      form: {},
      dialogFormVisible: false,
      multipleSelection: []
    }
  },
  created() {
    this.load()
  },
  methods: {
    load() {
      this.request.get("/user/page", {
        params: {
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          username: this.username,
          email: this.email,
          address: this.address,
        }
      }).then(res => {
        console.log(res)

        this.tableData = res.records
        this.total = res.total

      })
    },
    save() {
      this.request.post("/user", this.form).then(res => {
        if (res) {
          this.$message.success("Save Success")
          this.dialogFormVisible = false
          this.load()
        } else {
          this.$message.error("Save Failure")
        }
      })
    },
    handleAdd() {
      this.dialogFormVisible = true
      this.form = {}
    },
    handleEdit(row) {
      this.form = row
      this.dialogFormVisible = true
    },
    del(id) {
      this.request.delete("/user/" + id).then(res => {
        if (res) {
          this.$message.success("Delete Success")
          this.load()
        } else {
          this.$message.error("Delete Failure")
        }
      })
    },
    handleSelectionChange(val) {
      console.log(val)
      this.multipleSelection = val
    },
    delBatch() {
      let ids = this.multipleSelection.map(v => v.id)  // [{}, {}, {}] => [1,2,3]
      this.request.post("/user/del/batch", ids).then(res => {
        if (res) {
          this.$message.success("Delete Batch Success")
          this.load()
        } else {
          this.$message.error("Delete Batch Failure")
        }
      })
    },
    reset() {
      this.username = ""
      this.email = ""
      this.address = ""
      this.load()
    },
    handleSizeChange(pageSize) {
      console.log(pageSize)
      this.pageSize = pageSize
      this.load()
    },
    handleCurrentChange(pageNum) {
      console.log(pageNum)
      this.pageNum = pageNum
      this.load()
    }
  }
}
</script>

<style>
.headerBg {
  background: #eee!important;
}
</style>