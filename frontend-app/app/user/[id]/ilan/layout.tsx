import { CookiesProvider } from "next-client-cookies/server";

export default function Layout({
    children,
  }: Readonly<{
    children: React.ReactNode;
  }>) {
    
    return (
    <div>
        {children}
    </div>
    );
  }