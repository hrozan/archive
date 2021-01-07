use std::io;

fn read_weight_on_earth() -> f32 {
    println!("Enter your weith on Earth:");
    let mut input = String::new();
    io::stdin().read_line(&mut input).unwrap();
    input.trim().parse().unwrap()
}

fn weight_on_mercury(m: f32) -> f32 {
    m * 3.8
}

fn weight_on_venus(m: f32) -> f32 {
    m * 9.1
}

fn weight_on_mars(m: f32) -> f32 {
    m * 3.8
}

fn weight_on_jupiter(m: f32) -> f32 {
    m * 23.4
}

fn weight_on_saturn(m: f32) -> f32 {
    m * 9.3
}

fn weight_on_uranus(m: f32) -> f32 {
    m * 9.2
}

fn weight_on_neptune(m: f32) -> f32 {
    m * 11.2
}

fn main() {
    let weight_on_earth = read_weight_on_earth();
    let mass = weight_on_earth / 9.81;

    println!("Mecury: {}", weight_on_mercury(mass));
    println!("Venus: {}", weight_on_venus(mass));
    println!("Mars: {}", weight_on_mars(mass));
    println!("Jupiter: {}", weight_on_jupiter(mass));
    println!("Saturn: {}", weight_on_saturn(mass));
    println!("Uranus: {}", weight_on_uranus(mass));
    println!("Neptune: {}", weight_on_neptune(mass));
}
