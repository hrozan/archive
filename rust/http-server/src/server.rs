use std::convert::TryFrom;
use std::io::Error;
use std::io::Read;
use std::net;
use std::net::{SocketAddr, TcpListener, TcpStream};

use crate::http::Request;

pub struct Server {
    addr: String,
}

impl Server {
    pub fn new(addr: String) -> Self {
        Self { addr }
    }

    pub fn run(self) {
        let listener = TcpListener::bind(&self.addr).unwrap();
        println!("Server running on: {}", self.addr);
        loop {
            match listener.accept() {
                Ok((mut stream, _)) => {
                    let mut buffer = [0; 1024];
                    match stream.read(&mut buffer) {
                        Ok(_) => {
                            println!("Received a request: {}", String::from_utf8_lossy(&buffer));
                            match Request::try_from(&buffer[..]) {
                                Ok(request) => {}
                                Err(e) => { println!("Failed to parse request: {}", e) }
                            }
                        }
                        Err(e) => { println!("Failed to read from connection: {}", e) }
                    }
                }
                Err(e) => { println!("Failed to establish a connection: {}", e) }
            }
        }
    }
}
