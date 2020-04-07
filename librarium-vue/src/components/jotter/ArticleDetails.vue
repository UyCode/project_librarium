<template>
  <div class="articles-area">
    <el-backtop :bottom="100"></el-backtop>
    <el-row style="margin: 30px 0 0 500px">
      <el-breadcrumb separator="    ">
        <el-breadcrumb-item :to="{ path: '/jotter'}">文章列表</el-breadcrumb-item>
        <el-breadcrumb-item :to="{ path: '/library'}">图书馆</el-breadcrumb-item>
        <el-breadcrumb-item :to="{ path: '/admin/dashboard'}">管理中心</el-breadcrumb-item>
        <el-breadcrumb-item :to="{ path: ''}"></el-breadcrumb-item>
      </el-breadcrumb>
    </el-row>
    <el-card style="text-align: left;width: 990px;margin: 35px auto 0 auto">
      <div>
        <span style="font-size: 20px"><strong>{{article.articleTitle}}</strong></span>
        <el-divider content-position="left">{{article.articleDate}}</el-divider>
        <div class="markdown-body">
          <div v-html="article.articleContentHtml"></div>
        </div>
      </div>
    </el-card>
  </div>
</template>

<script>
  /* eslint-disable */

  export default {
    name: 'ArticleDetails',

    data () {
      return {
        article: [],
        s_navigation: (() => {
          return this.navigation;
        })()
      }
    },
    mounted () {
      this.loadArticle()
    },
    methods: {
      loadArticle () {
        var _this = this
        this.$axios.get('/article/' + this.$route.query.id).then(resp => {
          if (resp && resp.data.code === 200) {
            _this.article = resp.data.result
          }
        })
      }
    }
  }
</script>

<style scoped>
  @import "../../styles/markdown.css";
</style>
