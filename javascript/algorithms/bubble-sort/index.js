const bubbleSort = (arr) => {
    let noSwap = false
    const length = arr.length - 1

    while (!noSwap) {

        let localSwap = false
        for (let i = 0; i < length; i++) {
            const current = arr[i]
            const next = arr[i + 1]

            if (current > next) {
                // Swap
                arr[i] = next
                arr[i + 1] = current

                localSwap = true
            }
        }

        if (!localSwap) noSwap = true

    }

}


const arr = [6, 3, 8, 4, 9, 12]
console.log(arr)

bubbleSort(arr)

console.log(arr)
