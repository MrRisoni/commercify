<?php
declare(strict_types=1);

use Phinx\Migration\AbstractMigration;

final class NewTableProductTags extends AbstractMigration
{

    public function change(): void
    {
        $productTags = $this->table('product_tags', ['signed' => false]);
        $productTags->addColumn('product_id', 'biginteger', ['signed' => false])
            ->addColumn('language_id', 'biginteger', ['signed' => false])
            ->addColumn('tag', 'string', ['limit' => 55])
            ->addForeignKey('product_id', 'products', 'id', ['delete'=> 'RESTRICT', 'update'=> 'RESTRICT'])
            ->addForeignKey('language_id', 'languages', 'id', ['delete'=> 'RESTRICT', 'update'=> 'RESTRICT'])
            ->create();
    }
}
