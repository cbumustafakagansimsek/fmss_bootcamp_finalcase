import { CookiesProvider } from "next-client-cookies/server"
import UserPanel from "./user-panel"

export default function UserLayout({
    children,params
  }: {
    children: React.ReactNode,
    params: {
        id: string
      }
  }) {
    return (
      <section>
        <CookiesProvider>
            <UserPanel id={params.id}></UserPanel>
            {children}
        </CookiesProvider>
      </section>
    )
  }