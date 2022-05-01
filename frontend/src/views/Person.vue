<template>
  <el-card style="width: 500px;">
    <el-form label-width="80px" size="small">
      <el-form-item label="Username">
        <el-input v-model="form.username" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="Lastname">
        <el-input v-model="form.lastname" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="Email">
        <el-input v-model="form.email" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="Phone">
        <el-input v-model="form.phone" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="Address">
        <el-input v-model="form.address" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="save">Confirm</el-button>
      </el-form-item>
    </el-form>
  </el-card>
</template>

<script>
export default {
  name: "Person",
  data() {
    return {
      form: {},
      user: localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {}
    }
  },
  created() {
    this.request.get("/user/username/" + this.user.username).then(res => {
      if (res.code === '200') {
        this.form = res.data
      }
    })
  },
  methods: {
    save() {
      this.request.post("/user", this.form).then(res => {
        if (res.data) {
          this.$message.success("Save success")
        } else {
          this.$message.error("Save failure")
        }
      })
    },
  }
}
</script>

<style scoped>

</style>
