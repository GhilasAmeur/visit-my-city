import { AccordionPanel } from './AccordionPanel'

export const Accordion = ({ title, children }) => {
   return (
      <>
         <AccordionPanel title={title}>{children}</AccordionPanel>
      </>
   )
}
