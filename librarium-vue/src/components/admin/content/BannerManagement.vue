<template>
  <div>
    <el-row style="margin: 18px 0px 0px 18px ">
      <el-breadcrumb separator-class="el-icon-arrow-right">
        <el-breadcrumb-item :to="{ path: '/admin/dashboard' }">管理中心</el-breadcrumb-item>
        <el-breadcrumb-item>内容管理</el-breadcrumb-item>
        <el-breadcrumb-item>图书管理</el-breadcrumb-item>
      </el-breadcrumb>
    </el-row>
    <el-card style="margin: 18px 2%;width: 95%">
      <el-table
        stripe
        style="width: 100%">
        <el-table-column
          type="selection"
          width="55">
        </el-table-column>
        <el-table-column
          label="广告名称"
          fit>
        </el-table-column>
        <el-table-column
          label="链接地址"
          fit>
        </el-table-column>
        <el-table-column
          label="图片地址"
          fit>
        </el-table-column>
        <el-table-column
          fixed="right"
          label="操作"
          width="120">
          <template slot-scope="scope">
            <el-button class="el-icon-edit" type="primary" size="mini">
              编辑
            </el-button>
            <el-button class="el-icon-delete" type="danger" size="mini">
              移除
            </el-button>
          </template>
        </el-table-column>
      </el-table>
      <div style="margin: 20px 0 20px 0;float: left">
        <el-button class="el-icon-close" type="warning" >取消选择</el-button>
        <el-button class="el-icon-delete" type="danger" >批量删除</el-button>
      </div>
    </el-card>
  </div>
</template>

<script>
    export default {
        name: 'BannerManagement'
    }
</script>

<style scoped>

</style>
