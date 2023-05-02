<template>
  <div id="app">
    <nav class="nav-bar">
      <!-- <router-link to="/">Home</router-link> | -->
      <!-- <router-link to="/about">About</router-link> | -->
      <router-link v-bind:to="{name: 'Philosophers'}">Philosophers</router-link> | 
      <router-link v-bind:to="{name: 'Favorites'}">Favorites</router-link>
    </nav>
    <router-view/>
  </div>
</template>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  background-color: #eae7dc;
  min-height: 100vh;
}

nav {
  padding: 30px;
}

nav a {
  font-weight: bold;
  color: #2c3e50;
}

nav a.router-link-exact-active {
  color: #d2691e;
}

.nav-bar {
  border-bottom: solid #d2691e 0.2em;
  margin-bottom: 3%;
  color: #2c3e50;
  background-color: #d8c3a5;
}
</style>
