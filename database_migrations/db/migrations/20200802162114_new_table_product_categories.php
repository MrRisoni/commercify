<?php
declare(strict_types=1);

use Phinx\Migration\AbstractMigration;

final class NewTableProductCategories extends AbstractMigration
{
    /**
     * Change Method.
     *
     * Write your reversible migrations using this method.
     *
     * More information on writing migrations is available here:
     * https://book.cakephp.org/phinx/0/en/migrations.html#the-change-method
     *
     * Remember to call "create()" or "update()" and NOT "save()" when working
     * with the Table class.
     */
    public function change(): void
    {
        $prodCats = $this->table('product_categories', ['signed' => false]);
        $prodCats->addColumn('title', 'string', ['limit' => 55])
            ->addIndex(['title'], ['unique' => true])
            ->create();
    }
}
