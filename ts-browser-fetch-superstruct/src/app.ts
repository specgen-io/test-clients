import * as echo from './test-service/echo';

declare global {
  interface Window {
    echoClient: (config: {baseURL: string}) => echo.EchoClient    
  }
}

window.echoClient = echo.client