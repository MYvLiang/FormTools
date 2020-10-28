import Vue from 'vue';
import Router from 'vue-router';
import EditForm from '../components/page/EditForm'
import Home from '../components/page/Home'
import ManagePage from '../components/page/ManagePage'

Vue.use(Router);

export default new Router({
    routes: [
        {
            path: '/',
            redirect: '/home'
        },
        {
            path: '/home',
            component: Home,
            meta: { title: '通用表单工具' }
        },
        {
            path: '/editForm',
            component: EditForm,
            meta: { title: '编辑表单' }
        },
        {
            path: '/managePage',
            component: ManagePage,
            meta: { title: '通用表单工具' }
        },
        {
            path: '*',
            component: () => import('../components/page/404.vue'),
            meta: { title: '404' }
        }
    ]
});
