# Introduction
This is a local web app for RCC Detailing. You can run it in your system and the access the app in your web browser.
# Prerequisites
* You know the basics like running a command and navigating to desired directory.
# Set up (need to do just once)
## Step 1: Install Docker Desktop in your system
### macOS

1. **Download Docker Desktop:**
    - Visit the Docker Desktop for Mac page: [Docker Desktop for Mac](https://www.docker.com/products/docker-desktop)
    - Click "Download for Mac (Apple silicon)" if you have an Apple M1/M2 chip, or "Download for Mac (Intel chip)" if you have an Intel processor.

2. **Install Docker Desktop:**
    - Open the downloaded `.dmg` file.
    - Drag `Docker.app` to the `Applications` folder.
    - Launch Docker from the `Applications` folder.

3. **Verify Installation:**
    - Once Docker Desktop starts, you will see the Docker whale icon in the menu bar.
    - Open a terminal and run the following command to verify the installation:
      ```sh
      docker --version
      ```
    - You should see the Docker version information displayed.

### Windows

1. **Download Docker Desktop:**
    - Visit the Docker Desktop for Windows page: [Docker Desktop for Windows](https://www.docker.com/products/docker-desktop)
    - Click "Download for Windows".

2. **Install Docker Desktop:**
    - Run the downloaded installer.
    - Follow the installation wizard instructions.
    - During the installation process, ensure the "Install required components for WSL 2" option is selected. WSL 2 is required for Docker Desktop on Windows.

3. **Enable WSL 2:**
    - If you haven't already, you need to enable the WSL 2 feature. Open PowerShell as Administrator and run:
      ```sh
      dism.exe /online /enable-feature /featurename:Microsoft-Windows-Subsystem-Linux /all /norestart
      dism.exe /online /enable-feature /featurename:VirtualMachinePlatform /all /norestart
      ```
    - Download and install the Linux kernel update package from [here](https://aka.ms/wsl2kernel).
    - Set WSL 2 as the default version by running:
      ```sh
      wsl --set-default-version 2
      ```

4. **Start Docker Desktop:**
    - Once installed, Docker Desktop should start automatically. If not, you can start it from the Start menu.

5. **Verify Installation:**
    - Once Docker Desktop starts, you will see the Docker whale icon in the system tray.
    - Open Command Prompt or PowerShell and run the following command to verify the installation:
      ```sh
      docker --version
      ```
    - You should see the Docker version information displayed.

### Additional Resources

- [Docker Desktop Documentation](https://docs.docker.com/desktop/)
- [Getting Started with Docker](https://docs.docker.com/get-started/)

By following these instructions, you will have Docker Desktop installed on your macOS or Windows machine, ready to build and run Docker containers.
## Step 2: Opening a Terminal Session

### macOS

To open a terminal session on macOS, follow these steps:

1. **Using Spotlight Search:**
    - Press `Cmd (⌘) + Space` to open Spotlight Search.
    - Type `Terminal` and press `Enter`.

2. **Using Finder:**
    - Open Finder.
    - Go to `Applications` > `Utilities`.
    - Double-click `Terminal`.

3. **Using Launchpad:**
    - Open Launchpad by clicking the Launchpad icon in the Dock or pressing `F4`.
    - Type `Terminal` in the search bar.
    - Click the `Terminal` icon to open it.

Once the terminal is open, you can type commands and execute them by pressing `Enter`.

### Windows

To open a terminal session on Windows, follow these steps:

1. **Using Command Prompt:**
    - Press `Win + R` to open the Run dialog.
    - Type `cmd` and press `Enter`.

2. **Using PowerShell:**
    - Press `Win + X` or right-click the Start button.
    - Select `Windows PowerShell` or `Windows PowerShell (Admin)` for an elevated session.

3. **Using Windows Terminal (if installed):**
    - Press `Win + X` or right-click the Start button.
    - Select `Windows Terminal` or `Windows Terminal (Admin)` for an elevated session.

Once the terminal is open, you can type commands and execute them by pressing `Enter`.

These steps will help you open a terminal session on both macOS and Windows, allowing you to execute the necessary commands to work with Docker and other tools.


## Step 3: Clone this repo
* Navigate to the directory in the terminal`(cd /path/to/your/dir)` where you would like put the code
* To clone the repository, run the following command:

```sh
git clone https://github.com/bibhab20/rcc-local.git
cd combined-repo
```

# Run and access the app
## Step 1: Build and run the app
* Make sure you in rcc-local directory.(By default you should be if you are following the steps along for the first time set up)
* You can check that by running the command `pwd`(for mac) or `echo %cd%`(for windows)in your terminal. It should end with `rcc-local`
* If you are running it for the first time. Give execution permission to the start.sh file
```shell
chmod +x start.sh
```
* To build and run the frontend and backend apps, execute the start.sh script:
```shell
./start.sh
```
This will build the Docker images and start the containers for both the backend and frontend applications.
## Step 2: Access the app
* Frontend: Open your browser and navigate to http://localhost:3000.
* Backend: The backend application will be running on http://localhost:8080 (you don't need to access this)

# Accessing logs
In case of any failures or unexpected behaviour you might want to check the logs. Follow the steps below to access the logs of individual apps for combined.
Run any one of the commands to print the logs in your terminal
### backend app
```shell
docker logs rcc-backend
docker-compose logs rcc-backend
```

### frontend app
```shell
docker logs rcc-frontend
docker-compose logs rcc-frontend
```
### Combined
```shell
docker-compose logs -f
```
