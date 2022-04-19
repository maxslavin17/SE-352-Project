<template>
  <div>
    <el-container>
      <el-aside width="200px" style="background-color: rgb(238, 241, 246);">
        <el-menu :default-openeds="['1', '3']" style="min-height: 100%">
          <el-submenu index="1">
            <template slot="title"><i class="el-icon-message"></i>NAVIGATION ONE</template>
            <el-menu-item-group>
              <template slot="title">GROUP ONE</template>
              <el-menu-item index="1-1">Option1</el-menu-item>
              <el-menu-item index="1-2">Option2</el-menu-item>
            </el-menu-item-group>
            <el-menu-item-group title="GROUP TWO">
              <el-menu-item index="1-3">Option3</el-menu-item>
            </el-menu-item-group>
            <el-submenu index="1-4">
              <template slot="title">Option4</template>
              <el-menu-item index="1-4-1">Option4-1</el-menu-item>
            </el-submenu>
          </el-submenu>
          <el-submenu index="2">
            <template slot="title"><i class="el-icon-menu"></i>NAVIGATION ONE</template>
            <el-menu-item-group>
              <template slot="title">GROUP ONE</template>
              <el-menu-item index="2-1">Option1</el-menu-item>
              <el-menu-item index="2-2">Option2</el-menu-item>
            </el-menu-item-group>
            <el-menu-item-group title="GROUP TWO">
              <el-menu-item index="2-3">Option3</el-menu-item>
            </el-menu-item-group>
            <el-submenu index="2-4">
              <template slot="title">Option4</template>
              <el-menu-item index="2-4-1">Option4-1</el-menu-item>
            </el-submenu>
          </el-submenu>
          <el-submenu index="3">
            <template slot="title"><i class="el-icon-setting"></i>NAVIGATION ONE</template>
            <el-menu-item-group>
              <template slot="title">GROUP ONE</template>
              <el-menu-item index="3-1">Option1</el-menu-item>
              <el-menu-item index="3-2">Option2</el-menu-item>
            </el-menu-item-group>
            <el-menu-item-group title="GROUP TWO">
              <el-menu-item index="3-3">Option3</el-menu-item>
            </el-menu-item-group>
            <el-submenu index="3-4">
              <template slot="title">Option4</template>
              <el-menu-item index="3-4-1">Option4-1</el-menu-item>
            </el-submenu>
          </el-submenu>
        </el-menu>
      </el-aside>

      <el-container>

        <el-header style="text-align: right; font-size: 12px; border-bottom: 1px solid #ccc; line-height: 60px">
          <el-dropdown>
            <i class="el-icon-setting" style="margin-right: 15px"></i>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item>check</el-dropdown-item>
              <el-dropdown-item>add</el-dropdown-item>
              <el-dropdown-item>delete</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
          <span>User</span>
        </el-header>

        <el-main>
          <el-table :data="tableData">
            <el-table-column prop="date" label="DATE" width="140">
            </el-table-column>
            <el-table-column prop="name" label="NAME" width="120">
            </el-table-column>
            <el-table-column prop="address" label="ADDRESS">
            </el-table-column>
          </el-table>
        </el-main>

      </el-container>
    </el-container>
  </div>
</template>

<script>

export default {
  name: 'Home',
  data() {
    const item = {
      date: '2022-04-18',
      name: 'James Bond',
      address: 'Chicago'
    };
    return {
      tableData: Array(20).fill(item),
      msg: "hello 452"
    }
  }
}
</script>