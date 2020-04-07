<template>
  <div>
    <el-dialog
      title="修改用户信息"
      :visible.sync="dialogFormVisible">
      <el-form v-model="selectedUser" style="text-align: left" ref="dataForm">
        <el-form-item label="用户名" label-width="120px" prop="username">
          <label>{{selectedUser.username}}</label>
        </el-form-item>
        <el-form-item label="真实姓名" label-width="120px" prop="name">
          <el-input v-model="selectedUser.name" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="手机号" label-width="120px" prop="phone">
          <el-input v-model="selectedUser.phone" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="邮箱" label-width="120px" prop="email">
          <el-input v-model="selectedUser.email" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="密码" label-width="120px" prop="password">
          <el-button type="warning"
                     @click="resetPassword(selectedUser.username)"
                     class="el-icon-check">重置密码</el-button>
        </el-form-item>
        <el-form-item label="角色分配" label-width="120px" prop="roles">
          <el-checkbox-group v-model="selectedRolesIds">
              <el-checkbox v-for="(role,i) in roles" :key="i" :label="role.id">{{role.nameZh}}</el-checkbox>
          </el-checkbox-group>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false" class="el-icon-close" type="warning">取 消</el-button>
        <el-button type="primary" @click="onSubmit(selectedUser)" class="el-icon-check" >确 定</el-button>
      </div>
    </el-dialog>
    <el-row style="margin: 18px 0px 0px 18px ">
      <el-breadcrumb separator-class="el-icon-arrow-right">
        <el-breadcrumb-item :to="{ path: '/admin/dashboard' }">管理中心</el-breadcrumb-item>
        <el-breadcrumb-item>用户管理</el-breadcrumb-item>
        <el-breadcrumb-item>用户信息</el-breadcrumb-item>
      </el-breadcrumb>
    </el-row>
    <bulk-registration @onSubmit="listUsers()"></bulk-registration>
    <el-card style="margin: 18px 2%;width: 95%">
      <el-table
        :data="users"
        stripe
        :default-sort = "{prop: 'id', order: 'ascending'}"
        style="width: 100%"
        :max-height="tableHeight">
        <el-table-column
          type="selection"
          width="45">
        </el-table-column>
        <el-table-column
          prop="id"
          label="id"
          sortable
          width="50">
        </el-table-column>
        <el-table-column
          prop="username"
          label="用户名"
          width="120">
        </el-table-column>
        <el-table-column
          prop="name"
          label="真实姓名"
          width="120">
        </el-table-column>
        <el-table-column
          prop="phone"
          label="手机号"
          fit>
        </el-table-column>
        <el-table-column
          prop="email"
          label="邮箱"
          show-overflow-tooltip
          fit>
        </el-table-column>
        <el-table-column
          label="状态"
          width="50">
          <template slot-scope="scope">
            <el-switch
              v-model="scope.row.enabled"
              active-color="#13ce66"
              inactive-color="#ff4949"
              @change="(value) => commitStatusChange(value, scope.row)">
            </el-switch>
          </template>
        </el-table-column>
        <el-table-column
          label="操作"
          width="180">
          <template slot-scope="scope">
            <el-button
              @click="editUser(scope.row)"
              type="primary"
              size="mini" class="el-icon-edit">
              编辑
            </el-button>
            <el-button
              @click="remove(scope.row)"
              type="danger"
              size="mini"
              class="el-icon-delete">
              移除
            </el-button>
          </template>
        </el-table-column>
      </el-table>
      <div style="margin: 20px 0 20px 0;float: left">
        <el-button type="warning"  class="el-icon-close">取消选择</el-button>
        <el-button type="danger"  class="el-icon-delete">批量删除</el-button>
      </div>
    </el-card>
  </div>
</template>

<script>
  /* eslint-disable */
  import BulkRegistration from './BulkRegistration'
    export default {
      name: 'UserProfile',
      components: {BulkRegistration},
      data () {
          return {
            users: [],
            roles: [],
            dialogFormVisible: false,
            selectedUser: [],
            selectedRolesIds: []
          }
      },
      mounted () {
        this.listUsers()
        this.listRoles()
      },
      computed: {
        tableHeight () {
          return window.innerHeight - 320
        }
      },
      methods: {
        remove (row) {
          this.$confirm(`此操作将永久移除该${row.name}是否继续?`, '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
          const rowName = row.name
          this.$axios.delete('/admin/user/deleteUserBy/'+row.id).then( resp =>{
            if(resp && resp.data.code === 200){
              this.listUsers()
              this.listRoles()
              this.$notify.success({
                title: '移除成功',
                message: `你已经移除了${rowName}`
              })
            }
          }).catch(error=> {
            this.$notify.error({
              title: '网络错误！',
              message: '请稍后重试！'
            })
          })
          })
        },
        listUsers () {
          var _this = this
          this.$axios.get('/admin/user').then(resp => {
            console.log('listed')
            if (resp && resp.data.code === 200) {
              this.users = resp.data.result
            }
          })
        },
        listRoles () {
          var _this = this
          this.$axios.get('/admin/role').then(resp => {
            if (resp && resp.data.code === 200) {
              this.roles = resp.data.result
            }
          })
        },
        commitStatusChange (value, user) {
          if (user.username !== 'admin') {
            this.$axios.put('/admin/user/status', {
              enabled: value,
              username: user.username
            }).then(resp => {
              if (resp && resp.data.code === 200) {
                if (value) {
                  this.$message('用户 [' + user.username + '] 已启用')
                } else {
                  this.$message('用户 [' + user.username + '] 已禁用')
                }
              }
            })
          } else {
            user.enabled = true
            this.$alert('不能禁用管理员账户')
          }
        },
        onSubmit (user) {
          let _this = this
          // 根据视图绑定的角色 id 向后端传送角色信息
          let roles = []
          for (let i = 0; i < _this.selectedRolesIds.length; i++) {
            for (let j = 0; j < _this.roles.length; j++) {
              if (_this.selectedRolesIds[i] === _this.roles[j].id) {
                roles.push(_this.roles[j])
              }
            }
          }
          this.$axios.put('/admin/user', {
            username: user.username,
            name: user.name,
            phone: user.phone,
            email: user.email,
            roles: roles
          }).then(resp => {
            if (resp && resp.data.code === 200) {
              this.$alert('用户信息修改成功')
              this.dialogFormVisible = false
              // 修改角色后重新请求用户信息，实现视图更新
              this.listUsers()
            }
          })
        },
        editUser (user) {
          this.dialogFormVisible = true
          this.selectedUser = user
          let roleIds = []
          for (let i = 0; i < user.roles.length; i++) {
            roleIds.push(user.roles[i].id)
          }
          this.selectedRolesIds = roleIds
        },
        resetPassword (username) {
          this.$axios.put('/admin/user/password', {
            username: username
          }).then(resp => {
            if (resp && resp.data.code === 200) {
              this.$alert('密码已重置为 123')
          }
          })
        }
      }
    }
</script>

<style scoped>

</style>
