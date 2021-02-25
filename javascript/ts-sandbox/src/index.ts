import {Option, some, none, isSome} from "fp-ts/Option";

console.log("🚀")

const sum2 = (x: number): Option<number> => {
    if (x !== 2) {
        return some(2 * x)
    }
    return none
}


const result = sum2(3)


if (isSome(result)) {
    console.log(result.value)
} else {
    console.error("Error")
}

console.log(result)
