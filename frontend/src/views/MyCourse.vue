<template>
  <div>
    <div style="margin: 10px 0">
      <el-input style="width: 200px" placeholder="Please Input Coursename" suffix-icon="el-icon-search" v-model="mycname"></el-input>
      <el-button class="ml-5" type="primary" @click="load">Search</el-button>
      <el-button type="primary" @click="reset">Reset</el-button>
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
        <el-button type="primary" slot="reference">DeleteBatch <i class="el-icon-remove-outline"></i></el-button>
      </el-popconfirm>
    </div>

    <el-table :data="tableData" border stripe :header-cell-class-name="'headerBg'"  @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55"></el-table-column>
      <el-table-column prop="id" label="ID" width="80"></el-table-column>
      <el-table-column prop="mycid" label="Course Identifier" width="280"></el-table-column>
      <el-table-column prop="mycname" label="Name" width="235"></el-table-column>
      <el-table-column prop="mydescription" label="Description" width="400"></el-table-column>
      <el-table-column label="Operation"  width="500" align="center">
        <template slot-scope="scope">
<!--          <el-button type="primary" @click="handleEdit(scope.row)">Enroll <i class="el-icon-edit"></i></el-button>-->
          <el-popconfirm
              class="ml-5"
              confirm-button-text='YES'
              cancel-button-text='NO'
              icon="el-icon-info"
              icon-color="red"
              title="Are you sure？"
              @confirm="del(scope.row.id)"
          >
            <el-button type="primary" slot="reference"> Delete<i class="el-icon-remove-outline"></i></el-button>
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

    <el-dialog title="Course Information" :visible.sync="dialogFormVisible" width="30%" >
      <el-form label-width="80px" size="small">
        <el-form-item label="course id">
          <el-input v-model="form.mycid" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="name">
          <el-input v-model="form.mycname" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="description">
          <el-input v-model="form.mydescription" autocomplete="off"></el-input>
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
  name: "mycourse",
  data() {
    return {
      tableData: [],
      total: 0,
      pageNum: 1,
      pageSize: 10,
      mycname: "",
      form: {},
      dialogFormVisible: false,
      multipleSelection: [],
      user: localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {}
    }
  },
  created() {
    this.load();
  },
  methods: {
    load() {
      // this.request.get("/mycourse/page", {
      //   params: {
      //     pageNum: this.pageNum,
      //     pageSize: this.pageSize,
      //     username: this.username,
      //     email: this.email,
      //     address: this.address,
      //   }
      // }).then(res => {
      //   console.log(res)
      //
      //   this.tableData = res.records
      //   this.total = res.total
      //
      // })
      this.request.get("/mycourse/user/" + this.user.id).then(res => {
        console.log(res)
        this.tableData = res
      })
    },
    saveAll() {
      this.request.post("/mycourse", this.tableData).then(res => {
        if (res) {
          this.$message.success("Save All Success")
        } else {
          this.$message.error("Save All Failure")
        }
      })
    },
    save() {
      this.request.post("/mycourse", this.form).then(res => {
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
      this.request.delete("/mycourse/user/" + this.user.id + "/" + id).then(res => {
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
      this.request.post("/mycourse/del/batch", ids).then(res => {
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