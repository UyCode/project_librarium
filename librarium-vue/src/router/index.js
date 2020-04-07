/* eslint-disable */
import Vue from 'vue'
import Router from 'vue-router'
import AppIndex from '../components/home/AppIndex'
import Articles from '../components/jotter/Articles'
import Editor from '../components/admin/content/ArticleEditor'
import LibraryIndex from '../components/library/LibraryIndex'
import Login from '../components/Login'
import Home from '../components/Home'
import AdminIndex from '../components/admin/AdminIndex'
import Register from '../components/Register'
import DashBoard from '../components/admin/dashboard/admin/index'
import ArticleDetails from '../components/jotter/ArticleDetails'
import Error404 from '../components/pages/Error404'

Vue.use(Router)

export default new Router({
  mode: 'history',
  routes: [
    {
      path: '/',
      name: 'Default',
      redirect: '/home',
      component: Home
    },
    {
      path: '/jotter/article',
      name: 'Article',
      component: ArticleDetails
    },
    {
      path: '/admin/content/editor',
      name: 'Editor',
      component: Editor,
      meta: {
        requireAuth: true
      }
    },
    {
      // home页面并不需要被访问，只是作为其它组件的父组件
      path: '/home',
      name: 'Home',
      component: Home,
      redirect: '/library',
      children: [
        {
          path: '/index',
          name: 'AppIndex',
          component: AppIndex
        },
        {
          path: '/jotter',
          name: 'Jotter',
          component: Articles
        },
        {
          path: '/library',
          name: 'Library',
          component: LibraryIndex
        }
      ]
    },
    {
      path: '/login',
      name: 'Login',
      component: Login
    },
    {
      path: '/register',
      name: 'Register',
      component: Register
    },
    {
      path: '/admin',
      name: 'Admin',
      component: AdminIndex,
      meta: {
        requireAuth: true
      },
      children: [
        {
          path: '/admin/dashboard',
          name: 'Dashboard',
          component: DashBoard,
          meta: {
            requireAuth: true
          }
        }/*,
        {
          path: '/admin/user/book',
          name: 'BorrowedBooks',
          component: BorrowedBooks
        },
        {
          path: 'admin/user/profile',
          name: 'SingleUser',
          component: SingleUser
        }*/
      ]
    },
    {
      path: '*',
      component: Error404
    }
  ]
})

// 用于创建默认路由
export const createRouter = routes => new Router({
  mode: 'history',
  routes: [
    {
      path: '/',
      name: 'Default',
      redirect: '/home',
      component: Home
    },
    {
      path: '/jotter/article',
      name: 'Article',
      component: ArticleDetails
    },
    {
      path: '/admin/content/editor',
      name: 'Editor',
      component: Editor,
      meta: {
        requireAuth: true
      }
    },
    {
      // home页面并不需要被访问，只是作为其它组件的父组件
      path: '/home',
      name: 'Home',
      component: Home,
      redirect: '/library',
      children: [
        {
          path: '/index',
          name: 'AppIndex',
          component: AppIndex
        },
        {
          path: '/jotter',
          name: 'Jotter',
          component: Articles
        },
        {
          path: '/library',
          name: 'Library',
          component: LibraryIndex
        }
      ]
    },
    {
      path: '/login',
      name: 'Login',
      component: Login
    },
    {
      path: '/register',
      name: 'Register',
      component: Register
    },
    {
      path: '/admin',
      name: 'Admin',
      component: AdminIndex,
      meta: {
        requireAuth: true
      },
      children: [
        {
          path: '/admin/dashboard',
          name: 'Dashboard',
          component: DashBoard,
          meta: {
            requireAuth: true
          }
        }/*,
        {
          path: '/admin/user/book',
          name: 'BorrowedBooks',
          component: BorrowedBooks
        },
        {
          path: 'admin/user/profile',
          name: 'SingleUser',
          component: SingleUser
        }*/
      ]
    },
    {
      path: '*',
      component: Error404
    }
  ]
})
