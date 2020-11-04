const URL = 'http://localhost:8081';
let entries = [], categories = [];
let entriesData, editForm, editCheckIn, editCheckOut, editId;

const formatDate = (dateString, timeString) => {
    return new Date(`${dateString}T${timeString}`).toISOString();
}

const formatDateToHTML = (timeString) => {
    return new Date(`${timeString}`).toDateStlring();
}

const createEntry = (e) => {
    e.preventDefault();
    const formData = new FormData(e.target);
    const entry = {};
    entry['checkIn'] = formatDate(formData.get('checkInDate'), formData.get('checkInTime'));
    entry['checkOut'] = formatDate(formData.get('checkOutDate'), formData.get('checkOutTime'));

    fetch(`${URL}/entries`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(entry)
    }).then((result) => {
        result.json().then((entry) => {
            if (result.status === 201) {
                entries.push(entry);
                renderEntries();
            } else {
                alert("Checkout can't be before checkin!")
            }
        });
    });
};

const deleteEntry = (id) => {
    fetch(`${URL}/entries/${id}`, {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json'
        }
    })
    .then(res => {
        loadEntries()
    })
    .catch(err => {
        console.log(err)
    })
}

const editEntry = (e) => {
    e.preventDefault();
    const formData = new FormData(e.target);
    const entry = {};
    entry['id'] = formData.get('editId');
    entry['editCheckIn'] = formatDate(formData.get('editCheckInDate'), formData.get('editCheckInTime'));
    entry['editCheckOut'] = formatDate(formData.get('editCheckOutDate'), formData.get('editCheckOutTime'));

    fetch(`${URL}/entries/${entry['id']}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(entry)
    })
        .then(res => {
            loadEntries()
        })
        .catch(err => {
            console.log(err)
        })
}

const loadEntries = () => {
    fetch(`${URL}/entries`, {
        method: 'GET'
    }).then((result) => {
        result.json().then((result) => {
            entries = result;
            renderEntries();
        });
    });
    renderEntries();
};

const createCell = (text) => {
    const cell = document.createElement('td');
    cell.innerText = text;
    return cell;
};

const editButton = (id) => {
    const td = document.createElement('td');
    const button = document.createElement('button');
    button.innerText = "Edit";
    button.addEventListener('click', () => toggleEditEntryForm(id));
    td.appendChild(button);
    return button;
}

const deleteButton = (id) => {
    const td = document.createElement('td');
    const button = document.createElement('button');
    button.innerText = "Delete";
    button.addEventListener('click', () => deleteEntry(id))
    td.appendChild(button);
    return td;
}

const toggleEditEntryForm = (id) => {
    const entry = getEntry(id);
    editId.innerText = id;
    editCheckIn.value = entry.checkIn;
    editCheckOut.value = entry.checkOut;
    editForm.style.display = "block";
}

const getEntry = (id) => {
    return entries.filter(entry => entry.id === id)[0];
}

const renderEntries = () => {
    const display = document.querySelector('#entriesData');
    display.innerHTML = '';
    entries.forEach((entry) => {
        const row = document.createElement('tr');
        row.appendChild(createCell(entry.id));
        row.appendChild(createCell(new Date(entry.checkIn).toLocaleString()));
        row.appendChild(createCell(new Date(entry.checkOut).toLocaleString()));
        row.appendChild(createCell(entry.category));
        row.appendChild(deleteButton(entry.id));
        row.appendChild(editButton(entry.id, entry));
        display.appendChild(row);
    });
};


const bindItems = () => {
    entriesData = document.getElementById('entriesData');
    editForm = document.getElementById('editEntryForm');
    editCheckIn = document.getElementById('editCheckIn');
    editCheckOut = document.getElementById('editCheckOut');
    editId = document.getElementById('editId');
}

document.addEventListener('DOMContentLoaded', function(){
    const createEntryForm = document.querySelector('#createEntryForm');
    createEntryForm.addEventListener('submit', createEntry);
    const editEntryForm = document.querySelector('#editEntryForm');
    editEntryForm.addEventListener('submit', editEntry);
    bindItems();
    loadEntries();
});