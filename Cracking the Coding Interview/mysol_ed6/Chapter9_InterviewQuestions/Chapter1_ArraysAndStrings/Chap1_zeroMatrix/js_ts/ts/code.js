/*
 * CTCI Chapter 1.8 – Zero Matrix
 *
 * If an element in an MxN matrix is 0,
 * set its entire row and column to 0.
 */
function setZeros(matrix) {
    const m = matrix.length;
    const n = matrix[0].length;
    const rows = new Array(m).fill(false);
    const cols = new Array(n).fill(false);
    // Step 1: mark rows and columns
    for (let i = 0; i < m; i++) {
        for (let j = 0; j < n; j++) {
            if (matrix[i][j] === 0) {
                rows[i] = true;
                cols[j] = true;
            }
        }
    }
    // Step 2: nullify matrix
    for (let i = 0; i < m; i++) {
        for (let j = 0; j < n; j++) {
            if (rows[i] === true || cols[j] === true) {
                matrix[i][j] = 0;
            }
        }
    }
}
function setZerosOptimized(matrix) {
    const m = matrix.length;
    const n = matrix[0].length;
    let rowZero = false;
    let colZero = false;
    // Check first row
    for (let j = 0; j < n; j++) {
        if (matrix[0][j] === 0) {
            rowZero = true;
        }
    }
    // Check first column
    for (let i = 0; i < m; i++) {
        if (matrix[i][0] === 0) {
            colZero = true;
        }
    }
    // Use first row & column as markers
    for (let i = 1; i < m; i++) {
        for (let j = 1; j < n; j++) {
            if (matrix[i][j] === 0) {
                matrix[i][0] = 0;
                matrix[0][j] = 0;
            }
        }
    }
    // Nullify rows
    for (let i = 1; i < m; i++) {
        if (matrix[i][0] === 0) {
            for (let j = 0; j < n; j++) {
                matrix[i][j] = 0;
            }
        }
    }
    // Nullify columns
    for (let j = 1; j < n; j++) {
        if (matrix[0][j] === 0) {
            for (let i = 0; i < m; i++) {
                matrix[i][j] = 0;
            }
        }
    }
    // Nullify first row
    if (rowZero === true) {
        for (let j = 0; j < n; j++) {
            matrix[0][j] = 0;
        }
    }
    // Nullify first column
    if (colZero === true) {
        for (let i = 0; i < m; i++) {
            matrix[i][0] = 0;
        }
    }
}
/* ===============================
   Testing Section
=============================== */
let matrix1 = [
    [1, 2, 3],
    [4, 0, 6],
    [7, 8, 9]
];
// Deep copy
let matrix2 = JSON.parse(JSON.stringify(matrix1));
let output = "<h2>>> CTCI Chapter 1.8 – Zero Matrix</h2>";
output += "<b>Original:</b><br>" + JSON.stringify(matrix1) + "<br><br>";
setZeros(matrix1);
setZerosOptimized(matrix2);
output += "<b>Solution 1:</b><br>" + JSON.stringify(matrix1) + "<br><br>";
output += "<b>Solution 2:</b><br>" + JSON.stringify(matrix2);
document.querySelector('#t1').innerHTML = output;
