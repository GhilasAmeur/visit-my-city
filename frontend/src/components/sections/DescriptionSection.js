import { View } from 'react-native'
import { SectionTitle } from '../ui'
import { ExpandableText } from '../ui/typography/ExpandableText'

export default function DescriptionSection({ linesNumber, text }) {
   return (
      <View>
         <SectionTitle style={{ fontSize: 22 }}>Description</SectionTitle>
         <ExpandableText linesNumber={linesNumber} text={text} />
      </View>
   )
}
