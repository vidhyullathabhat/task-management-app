export async function createNewTasker(data) {

    const response = await fetch('/tasker', {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data),
    });
    return response;
}

export async function editTasker(name, tasker) {

    const response = await fetch('/tasker/'+name, {
        method: 'PUT',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(tasker),
    });
    return response;
}

export async function deleteTasker(name) {

    const response = await fetch('/tasker/'+name, {
        method: 'DELETE',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        }
    });
    return response;
}

export async function getAllTaskers() {
    const response = fetch('/tasker');
    return (await response).json();
}