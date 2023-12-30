async function uploadFile() {
    const fileInput = document.getElementById('fileInput');
    const file = fileInput.files[0];

    if (!file) {
        alert('Please select a file.');
        return;
    }

    const chunkSize = 1024 * 1024; // 1 MB chunks (adjust as needed)
    const totalChunks = Math.ceil(file.size / chunkSize);

    // Calculate the hash of the original file
    const originalFileHash = await calculateFileHash(file);

    // Bootstrap Progress Bar
    const progressBar = document.getElementById('progress-bar');

    // Record start time
    const startTime = new Date().getTime();

    // Upload chunks sequentially
    for (let i = 0; i < totalChunks; i++) {
        const startByte = i * chunkSize;
        const endByte = Math.min((i + 1) * chunkSize, file.size);
        const chunk = file.slice(startByte, endByte);

        const formData = new FormData();
        formData.append('file', chunk);
        formData.append('chunkNumber', i);
        formData.append('totalChunks', totalChunks);
        formData.append('fileName', file.name);
        formData.append('originalFileHash', originalFileHash);

        const uploadResponse = await fetch('/api/v1/file-storage/chunk-upload', {
            method: 'POST',
            headers: {
                'Content-Range': `bytes ${startByte}-${endByte}/${file.size}`,
            },
            body: formData,
        });

        if (!uploadResponse.ok) {
            alert(`Error uploading chunk ${i + 1}`);
            return;
        }

        // Update the progress bar
        const percentComplete = ((i + 1) / totalChunks) * 100;
        progressBar.style.width = percentComplete + '%';
        progressBar.setAttribute('aria-valuenow', percentComplete);
    }

    // Record end time
    const endTime = new Date().getTime();

    // Calculate total time taken
    const totalTime = endTime - startTime;
    console.log('Time taken: ' + totalTime + ' milliseconds');

    // Change the progress bar color to green upon completion
    progressBar.classList.remove('bg-info');
    progressBar.classList.add('bg-success');

    // Locate the result container
    const resultContainer = document.getElementById('result');

    // Display the success message in the result container
    resultContainer.innerHTML = `
        <div class="alert alert-success" role="alert">
            File upload complete! Time taken: ${totalTime} milliseconds
        </div>
    `;
}


async function calculateFileHash(file) {
    return new Promise((resolve, reject) => {
        const reader = new FileReader();
        reader.onload = (event) => {
            const arrayBuffer = event.target.result;
            crypto.subtle.digest('SHA-256', arrayBuffer).then(hashBuffer => {
                const hashArray = Array.from(new Uint8Array(hashBuffer));
                const hashHex = hashArray.map(byte => byte.toString(16).padStart(2, '0')).join('');
                resolve(hashHex);
            });
        };
        reader.onerror = (error) => {
            reject(error);
        };
        reader.readAsArrayBuffer(file);
    });
}