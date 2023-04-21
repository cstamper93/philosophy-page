import Vue from 'vue'
import VueRouter from 'vue-router'
//import HomeView from '../views/HomeView.vue'
import Philosophers from '../views/PhilosophersView.vue'
import Favorites from '../views/FavoritesView.vue'
import PhilosopherDetailsView from '../views/PhilosopherDetailsView.vue';

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'home',
    component: Philosophers
  },
  {
    path: '/about',
    name: 'about',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/AboutView.vue')
  },
  {
    path: '/philosophers',
    name: 'Philosophers',
    component: Philosophers
  },
  {
    path: '/favorites',
    name: 'Favorites',
    component: Favorites
  },
  // {
  //   path: '/philosopher-details',
  //   name: 'Philosopher-Details',
  //   component: PhilosopherDetails
  // },
  {
    path: '/philosopher-details-view',
    name: 'Philosopher-Details-View',
    component: PhilosopherDetailsView
  }
]

const router = new VueRouter({
  routes
})

export default router
