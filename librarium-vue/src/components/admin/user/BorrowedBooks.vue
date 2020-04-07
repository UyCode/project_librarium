<template>
  <div>
    <el-row style="height: 840px;">
      <el-switch class="switch"
                 v-model="values"
                 @change="changeView"
                 active-text="显示更多"></el-switch>
      <!--<view-switch class="switch"></view-switch>-->
      <el-tooltip effect="dark"
                  style="margin-top: 50px"
                  placement="right"
                  v-for="item in books.slice((currentPage-1)*pagesize,currentPage*pagesize)"
                  :key="item.id"
                  :disabled="disabled"
                  :open-delay="500">
        <p slot="content" style="font-size: 14px;margin-bottom: 6px;">{{item.title}}</p>
        <p slot="content" style="font-size: 13px;margin-bottom: 6px">
          <span>{{item.author}}</span> /
          <span>{{item.date}}</span> /
          <span>{{item.press}}</span>
        </p>
        <p slot="content" style="width: 300px" class="abstract">{{item.abs}}</p>
        <el-card class="card"
                 :body-style="{padding: '10px'}"
                 shadow="hover">
          <div class="cover">
            <img :src="item.cover" alt="封面">
          </div>
          <div class="info">
            <div class="title">
              <a href="">{{item.title}}</a>
            </div>
          </div>
          <div class="author">{{item.author}}</div>
          <el-button type="warning"
                     @click="deleteBook(item)"
                     :key="item.id"
                     style="float: bottom; width: 180px">
            <i class="el-icon-collection"></i>    归  还
          </el-button>
        </el-card>
      </el-tooltip>
    </el-row>
    <el-row>
      <el-pagination
        @current-change="handleCurrentChange"
        :current-page="currentPage"
        :page-size="pagesize"
        :total="books.length">
      </el-pagination>
    </el-row>
  </div>
</template>

<script>
  /* eslint-disable */
  import qs from 'qs';
  export default {
    name: 'BorrowedBooks',
    data () {
      return {
        disabled: false,
        values: true,
        books: [],
        currentPage: 1,
        pagesize: 8
      }
    },
    mounted: function () {
      this.loadBooks()
    },
    methods: {
      deleteBook (item) {
        const _this = this
        this.$axios.delete(`/bookReturn/${this.$store.state.id}/${item.id}`).then( resp =>{
          if(resp && resp.data.code === 200){
            var indexs = this.books.findIndex(item =>{
              if(item.id === resp.data.result){
                return true
              }
            })
            this.books.splice(indexs,1)
            this.$notify({
              message: `归还《${item.title}》成功！`,
              type: 'success'
            });
            /*window.location.reload()*/
          }
        }).catch( error =>{
          this.$notify({
            title: `未知错误！`,
            message: '请稍后重试看看呗！',
            type: 'error'
          });
        })
      },
      changeView (event) {
        this.disabled = !event
      },
      loadBooks () {
        var _this = this
        const userId = this.$store.state.id
        this.$axios.get(`/orderedBooks/${userId}`).then(resp => {
          if (resp && resp.data.code === 200) {
            this.books = resp.data.result
          }
        })
      },
      handleCurrentChange: function (currentPage) {
        this.currentPage = currentPage
      },
      searchResult () {
        var _this = this
        this.$axios
          .get('/search?keywords=' + this.$refs.searchBar.keywords, {
          }).then(resp => {
          if (resp && resp.data.code === 200) {
            _this.books = resp.data.result
          }
        })
      }
    }
  }
</script>
<style scoped>
  .card{
    width: 200px;
    margin-bottom: 20px;
    height: 350px;
    float: left;
    margin-right: 15px;
  }

  .cover {
    width: 180px;
    height: 250px;
    margin-bottom: 7px;
    overflow: hidden;
    cursor: pointer;
  }

  img {
    width: 175px;
    height: 250px;
    /*margin: 0 auto;*/
  }

  .title {
    font-size: 18px;
    text-align: left;
  }

  .author {
    color: #333;
    width: 102px;
    font-size: 12px;
    margin-bottom: 8px;
    text-align: left;
  }

  .abstract {
    display: block;
    line-height: 17px;
  }

  .el-icon-delete {
    cursor: pointer;
    float: right;
  }

  .switch {
    display: flex;
    position: absolute;
    left: 780px;
    top: 25px;
  }

  a {
    text-decoration: none;
  }

  a:link, a:visited, a:focus {
    color: #3377aa;
  }

</style>
