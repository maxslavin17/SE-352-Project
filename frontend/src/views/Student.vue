<template>
  <div>
    <div style="margin: 10px 0">
      <el-input style="width: 200px" placeholder="Enter student last name" suffix-icon="el-icon-search" ></el-input>
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
          title="Are you sure to delete this student？"
          @confirm="delBatch"
      >
        <el-button type="primary" slot="reference">DeleteBatch <i class="el-icon-remove-outline"></i></el-button>
      </el-popconfirm>
    </div>

    <el-table :data="tableData" border stripe :header-cell-class-name="'headerBg'"  @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55"></el-table-column>
      <el-table-column prop="id" label="ID" width="80"></el-table-column>
      <el-table-column prop="username" label="User Name" ></el-table-column>
      <el-table-column prop="sid" label="Student ID" ></el-table-column>
      <el-table-column prop="slastname" label="Student Last Name" ></el-table-column>
      <el-table-column prop="email" label="E-mail Address" ></el-table-column>
      
      <el-table-column label="Operation"  width="500" align="center">
        <template slot-scope="scope">
          <el-button type="primary" @click="handleEnroll(scope.row.id)">Enroll <i class="el-icon-edit"></i></el-button>
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

    <el-dialog title="Student Information" :visible.sync="dialogFormVisible" width="30%" >
      <el-form label-width="80px" size="small">
        <el-form-item label="Username">
          <el-input v-model="form.username" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="Student ID">
          <el-input v-model="form.sid" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="Student Last Name">
          <el-input v-model="form.slastname" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="Student Email">
          <el-input v-model="form.email" autocomplete="off"></el-input>
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
  name: "student",
  data() {
    return {
      tableData: [],
      total: 0,
      pageNum: 1,
      pageSize: 10,
      cname: "",
      form: {},
      dialogFormVisible: false,
      multipleSelection: [],
      user: localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {}
    }
  },
  created() {
    this.load()
  },
  methods: {
    load() {
      this.request.get("/student/page", {
        params: {
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          username: this.username,
          sid: this.sid,
        //   email: this.email,
        }
      }).then(res => {
        console.log(res)

        this.tableData = res.records
        this.total = res.total

      })
    },
    save() {
      this.request.post("/student", this.form).then(res => {
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
      this.request.delete("/student/" + id).then(res => {
        if (res) {
          this.$message.success("Student Deleted.")
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
      this.request.post("/student/del/batch", ids).then(res => {
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