<template>
  <div>
    <el-menu
      class="categories cards"
      default-active="0"
      @select="handleSelect"
      active-text-color="red">
      <el-menu-item index="0">
        <i class="el-icon-menu text_eff"></i>
        <span slot="title" class="text_eff">全部</span>
      </el-menu-item>
      <el-menu-item index="1">
        <i class="el-icon-menu text_eff"></i>
        <span slot="title" class="text_eff">文学</span>
      </el-menu-item>
      <el-menu-item index="2">
        <i class="el-icon-menu text_eff"></i>
        <span slot="title" class="text_eff">流行</span>
      </el-menu-item>
      <el-menu-item index="3">
        <i class="el-icon-menu text_eff"></i>
        <span slot="title" class="text_eff">文化</span>
      </el-menu-item>
      <el-menu-item index="4">
        <i class="el-icon-menu text_eff"></i>
        <span slot="title" class="text_eff">生活</span>
      </el-menu-item>
      <el-menu-item index="5">
        <i class="el-icon-menu text_eff"></i>
        <span slot="title" class="text_eff">经管</span>
      </el-menu-item>
      <el-menu-item index="6">
        <i class="el-icon-menu text_eff"></i>
        <span slot="title" class="text_eff">科技</span>
      </el-menu-item>
    </el-menu>
  </div>
</template>

<script>
    export default {
      name: 'SideMenu',
      data () {
        return {
          cid: ''
        }
      },
      methods: {
        handleSelect (key, keyPath) {
          this.cid = key
          this.$emit('indexSelect')
        }
      }
    }
</script>

<style scoped>
  .categories {
    position: fixed;
    margin-left: 50%;
    left: -600px;
    top: 100px;
    width: 150px;
  }
  .cards{
    border-radius:20px;
    border:#EBEEF5 none;
    background-color:#fff;
    overflow:hidden;
    color:#303133;
    -webkit-transition:.3s;
    transition:.3s;
    box-shadow:0 2px 12px 0 rgba(0,0,0,.1);
  }
  .cards:hover{
    padding: 10px;
    box-shadow:0 2px 12px 0 rgba(0,0,0,.5)
  }
  .text_eff{
    font-size: 14px;
    text-shadow: #2b2e33;
    -webkit-transition:.1s;
    transition:.1s;
  }
  .text_eff:hover{
    font-size: 20px;
    font-weight: bold;
    padding: 10px;
  }
</style>
