       <h4>Entry</h4>
      <div id="CreateEntry">
        <h1>Create Entry</h1>
        <input type="date" placeholder="checkIn" v-model="checkIn" />
        <input type="time" v-model="checkInTime" />
        <br />
        <input type="date" placeholder="checkOut" v-model="checkOut" />
        <input type="time" v-model="checkOutTime" />
        <br />
        <select v-model="room">
          <option disabled selected>Rooms</option>
          <option v-for="(room, index) in rooms" :key="index" :value="room">
            {{ room.name }}
          </option>
        </select>
        <br />
        <select v-model="category">
          <option disabled selected>Categories</option>
          <option
            v-for="(category, index) in categories"
            :key="index"
            :value="category"
          >
            {{ category.name }}
          </option>
        </select>
        <br />
        <button @click="updateValue">Update</button>