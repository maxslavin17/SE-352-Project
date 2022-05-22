<template>
  <div>
    <div style="margin: 10px 0">
      <el-input style="width: 200px" placeholder="Please Input Name" suffix-icon="el-icon-search" v-model="name"></el-input>
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
      <el-table-column prop="name" label="Name"></el-table-column>
      <el-table-column prop="description" label="Description" ></el-table-column>
<!--      <el-table-column prop="category" label="Category" ></el-table-column>-->
      <el-table-column label="Operation"  width="320" align="center">
        <template slot-scope="scope">
          <el-button type="primary" @click="selectMenu(scope.row.id)">Menu Access <i class="el-icon-menu"></i></el-button>
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

    <el-dialog title="Role" :visible.sync="dialogFormVisible" width="30%" >
      <el-form label-width="80px" size="small">
        <el-form-item label="Name">
          <el-input v-model="form.name" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="Description">
          <el-input v-model="form.description" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">Cancel</el-button>
        <el-button type="primary" @click="save">Confirm</el-button>
      </div>
    </el-dialog>

    <el-dialog title="Menu Manage" :visible.sync="menuDialogVis" width="30%">
      <el-tree
          :props="props"
          :data="menuData"
          show-checkbox
          node-key="id"
          ref="tree"
          :default-expanded-keys="expands"
          :default-checked-keys="checks">
        <span class="custom-tree-node" slot-scope="{ node, data }">
            <span>{{ data.name }}</span>
         </span>
      </el-tree>
      <div slot="footer" class="dialog-footer">
        <el-button @click="menuDialogVis = false">Cancel</el-button>
        <el-button type="primary" @click="saveRoleMenu">Confirm</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: "Role",
  data() {
    return {
      tableData: [],
      total: 0,
      pageNum: 1,
      pageSize: 10,
      name: "",
      form: {},
      dialogFormVisible: false,
      multipleSelection: [],
      menuDialogVis: false,
      menuData: [],
      props: {
        label: 'name',
      },
      expands: [],
      checks: [],
      roleId: 0,
      roleFlag: ''
    }
  },
  created() {
    this.load()
  },
  methods: {
    load() {
      this.request.get("/role/page", {
        params: {
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          name: this.name,
        }
      }).then(res => {
        console.log(res)

        this.tableData = res.data.records
        this.total = res.data.total

      })
    },
    save() {
      this.request.post("/role", this.form).then(res => {
        if (res.code === '200') {
          this.$message.success("Save Success")
          this.dialogFormVisible = false
          this.load()
        } else {
          this.$message.error("Save Failure")
        }
      })
    },
    saveRoleMenu() {
      this.request.post("/role/roleMenu/" + this.roleId, this.$refs.tree.getCheckedKeys()).then(res => {
        if (res.code === '200') {
          this.$message.success("Save Success")
          this.menuDialogVis = false

        } else {
          this.$message.error(res.msg)
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
      this.request.delete("/role/" + id).then(res => {
        if (res.code === '200') {
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
      this.request.post("/role/del/batch", ids).then(res => {
        if (res.code === '200') {
          this.$message.success("Delete Batch Success")
          this.load()
        } else {
          this.$message.error("Delete Batch Failure")
        }
      })
    },
    reset() {
      this.name = ""
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
    },
    selectMenu(roleID) {
      this.roleId = roleID
      this.menuDialogVis = true

      this.request.get("/menu").then(res => {
        this.menuData = res.data

        this.expands = this.menuData.map(value => value.id)
      })
      this.request.get("/role/roleMenu/" + this.roleId).then(res => {
        this.checks = res.data
      })

    },
    handleCheckChange(data, checked, indeterminate) {
      console.log(data, checked, indeterminate);
    },
  }
}
</script>

<style>
.headerBg {
  background: #eee!important;
}
</style>
